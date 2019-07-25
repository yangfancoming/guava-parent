

package com.google.common.eventbus;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.Queues;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Handler for dispatching events to subscribers, providing different event ordering guarantees that
 * make sense for different situations.
 *
 * <p><b>Note:</b> The dispatcher is orthogonal to the subscriber's {@code Executor}. The dispatcher
 * controls the order in which events are dispatched, while the executor controls how (i.e. on which
 * thread) the subscriber is actually called when an event is dispatched to it.
 *
 * @author Colin Decker
 */
abstract class Dispatcher {

    /**
     * Returns a dispatcher that queues events that are posted reentrantly on a thread that is already
     * dispatching an event, guaranteeing that all events posted on a single thread are dispatched to
     * all subscribers in the order they are posted.
     *
     * <p>When all subscribers are dispatched to using a <i>direct</i> executor (which dispatches on
     * the same thread that posts the event), this yields a breadth-first dispatch order on each
     * thread. That is, all subscribers to a single event A will be called before any subscribers to
     * any events B and C that are posted to the event bus by the subscribers to A.
     */
    static Dispatcher perThreadDispatchQueue() {
        return new PerThreadQueuedDispatcher();
    }

    /**
     * Returns a dispatcher that queues events that are posted in a single global queue. This behavior
     * matches the original behavior of AsyncEventBus exactly, but is otherwise not especially useful.
     * For async dispatch, an {@linkplain #immediate() immediate} dispatcher should generally be
     * preferable.
     */
    static Dispatcher legacyAsync() {
        return new LegacyAsyncDispatcher();
    }

    /**
     * Returns a dispatcher that dispatches events to subscribers immediately as they're posted
     * without using an intermediate queue to change the dispatch order. This is effectively a
     * depth-first dispatch order, vs. breadth-first when using a queue.
     */
    static Dispatcher immediate() {
        return ImmediateDispatcher.INSTANCE;
    }

    /** Dispatches the given {@code event} to the given {@code subscribers}. */
    // 分发器，用于将event分发给subscriber。它内部实现了三种不同类型的分发器，用于不同的情况下事件的顺序性。它的核心方法是：
    abstract void dispatch(Object event, Iterator<Subscriber> subscribers);

    /** Implementation of a {@link #perThreadDispatchQueue()} dispatcher. */
    private static final class PerThreadQueuedDispatcher extends Dispatcher {

        // This dispatcher matches the original dispatch behavior of EventBus.

        /** Per-thread queue of events to dispatch. */
        private final ThreadLocal<Queue<Event>> queue = ThreadLocal.withInitial(()->Queues.newArrayDeque());

        /** Per-thread dispatch state, used to avoid reentrant event dispatching. */
        private final ThreadLocal<Boolean> dispatching = ThreadLocal.withInitial(()->false);

        // EventBus默认使用的分发器。它的实现是通过ThreadLocal来实现一个事件队列，每个线程包含一个这样的内部队列。
        // 嵌套两层循环，第一层事件不为空，第二层该事件下的订阅者不为空，则分发事件下去。
        @Override
        void dispatch(Object event, Iterator<Subscriber> subscribers) {
            checkNotNull(event);
            checkNotNull(subscribers);
            Queue<Event> queueForThread = queue.get();
            queueForThread.offer(new Event(event, subscribers));

            if (!dispatching.get()) {
                dispatching.set(true);
                try {
                    Event nextEvent;
                    while ((nextEvent = queueForThread.poll()) != null) {
                        while (nextEvent.subscribers.hasNext()) {
                            nextEvent.subscribers.next().dispatchEvent(nextEvent.event);
                        }
                    }
                } finally {
                    dispatching.remove();
                    queue.remove();
                }
            }
        }

        private static final class Event {
            private final Object event;
            private final Iterator<Subscriber> subscribers;

            private Event(Object event, Iterator<Subscriber> subscribers) {
                this.event = event;
                this.subscribers = subscribers;
            }
        }
    }

    /** Implementation of a {@link #legacyAsync()} dispatcher. */
    private static final class LegacyAsyncDispatcher extends Dispatcher {

        // This dispatcher matches the original dispatch behavior of AsyncEventBus.
        //
        // We can't really make any guarantees about the overall dispatch order for this dispatcher in
        // a multithreaded environment for a couple reasons:
        //
        // 1. Subscribers to events posted on different threads can be interleaved with each other
        //    freely. (A event on one thread, B event on another could yield any of
        //    [a1, a2, a3, b1, b2], [a1, b2, a2, a3, b2], [a1, b2, b3, a2, a3], etc.)
        // 2. It's possible for subscribers to actually be dispatched to in a different order than they
        //    were added to the queue. It's easily possible for one thread to take the head of the
        //    queue, immediately followed by another thread taking the next element in the queue. That
        //    second thread can then dispatch to the subscriber it took before the first thread does.
        //
        // All this makes me really wonder if there's any value in queueing here at all. A dispatcher
        // that simply loops through the subscribers and dispatches the event to each would actually
        // probably provide a stronger order guarantee, though that order would obviously be different
        // in some cases.

        /** Global event queue. */
        private final ConcurrentLinkedQueue<EventWithSubscriber> queue =
                Queues.newConcurrentLinkedQueue();

        // AsyncEventBus使用的分发器。它在内部通过一个ConcurrentLinkedQueue的全局队列来存储事件。他和PerThreadQueuedDispatcher的主要区别在于分发循环这块
        // 是一前一后两个循环。前面一个是遍历事件订阅处理器，并构建一个事件实体对象存入队列。后一个循环是遍历该事件实体对象队列，取出事件实体对象中的事件进行分发。
        @Override
        void dispatch(Object event, Iterator<Subscriber> subscribers) {
            checkNotNull(event);
            while (subscribers.hasNext()) {
                queue.add(new EventWithSubscriber(event, subscribers.next()));
            }
            EventWithSubscriber e;
            while ((e = queue.poll()) != null) {
                e.subscriber.dispatchEvent(e.event);
            }
        }

        private static final class EventWithSubscriber {
            private final Object event;
            private final Subscriber subscriber;
            private EventWithSubscriber(Object event, Subscriber subscriber) {
                this.event = event;
                this.subscriber = subscriber;
            }
        }
    }

    /** Implementation of {@link #immediate()}. */
    private static final class ImmediateDispatcher extends Dispatcher {
        private static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();
        // 同步分发器。
        @Override
        void dispatch(Object event, Iterator<Subscriber> subscribers) {
            checkNotNull(event);
            while (subscribers.hasNext()) {
                subscribers.next().dispatchEvent(event);
            }
        }
    }
}
