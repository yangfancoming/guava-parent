package com.google.common.eventbus;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.util.concurrent.MoreExecutors;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Dispatches events to listeners, and provides ways for listeners to register themselves.
 *
 * <p>The EventBus allows publish-subscribe-style communication between components without requiring
 * the components to explicitly register with one another (and thus be aware of each other). It is
 * designed exclusively to replace traditional Java in-process event distribution using explicit
 * registration. It is <em>not</em> a general-purpose publish-subscribe system, nor is it intended
 * for interprocess communication.
 *
 * <h2>Receiving Events</h2>
 *
 * <p>To receive events, an object should:
 *
 * <ol>
 *   <li>Expose a public method, known as the <i>event subscriber</i>, which accepts a single
 *       argument of the type of event desired;
 *   <li>Mark it with a {@link Subscribe} annotation;
 *   <li>Pass itself to an EventBus instance's {@link #register(Object)} method.
 * </ol>
 *
 * <h2>Posting Events</h2>
 *
 * <p>To post an event, simply provide the event object to the {@link #post(Object)} method. The
 * EventBus instance will determine the type of event and route it to all registered listeners.
 *
 * <p>Events are routed based on their type &mdash; an event will be delivered to any subscriber for
 * any type to which the event is <em>assignable.</em> This includes implemented interfaces, all
 * superclasses, and all interfaces implemented by superclasses.
 *
 * <p>When {@code post} is called, all registered subscribers for an event are run in sequence, so
 * subscribers should be reasonably quick. If an event may trigger an extended process (such as a
 * database load), spawn a thread or queue it for later. (For a convenient way to do this, use an
 * {@link AsyncEventBus}.)
 *
 * <h2>Subscriber Methods</h2>
 *
 * <p>Event subscriber methods must accept only one argument: the event.
 *
 * <p>Subscribers should not, in general, throw. If they do, the EventBus will catch and log the
 * exception. This is rarely the right solution for error handling and should not be relied upon; it
 * is intended solely to help find problems during development.
 *
 * <p>The EventBus guarantees that it will not call a subscriber method from multiple threads
 * simultaneously, unless the method explicitly allows it by bearing the {@link
 * AllowConcurrentEvents} annotation. If this annotation is not present, subscriber methods need not
 * worry about being reentrant, unless also called from outside the EventBus.
 *
 * <h2>Dead Events</h2>
 *
 * <p>If an event is posted, but no registered subscribers can accept it, it is considered "dead."
 * To give the system a second chance to handle dead events, they are wrapped in an instance of
 * {@link DeadEvent} and reposted.
 *
 * <p>If a subscriber for a supertype of all events (such as Object) is registered, no event will
 * ever be considered dead, and no DeadEvents will be generated. Accordingly, while DeadEvent
 * extends {@link Object}, a subscriber registered to receive any Object will never receive a
 * DeadEvent.
 *
 * <p>This class is safe for concurrent use.
 *
 * <p>See the Guava User Guide article on <a
 * href="https://github.com/google/guava/wiki/EventBusExplained">{@code EventBus}</a>.
 *
 * @author Cliff Biffle
 * @since 10.0
 */
@Beta
public class EventBus {

    private static final Logger logger = Logger.getLogger(EventBus.class.getName());
    //  identifier表示：唯一标识，默认为default ，同一个应用中，可以根据identifier来区分不同的事件总线，只不过默认为default而已。
    private final String identifier;
    //多线程处理器，默认MoreExecutors.directExecutor()
    private final Executor executor;
    //异常处理器
    private final SubscriberExceptionHandler exceptionHandler;
    //订阅注册表
    private final SubscriberRegistry subscribers = new SubscriberRegistry(this);
    //消息分发器，默认为Dispatcher.perThreadDispatchQueue()，单线程消息分发队列
    private final Dispatcher dispatcher;

    /** Creates a new EventBus named "default". */
    public EventBus() {
        this("default");
    }

    /**
     * Creates a new EventBus with the given {@code identifier}.
     * @param identifier a brief name for this bus, for logging purposes. Should be a valid Java identifier.
     */
    public EventBus(String identifier) {
        this(identifier,MoreExecutors.directExecutor(),Dispatcher.perThreadDispatchQueue(), LoggingHandler.INSTANCE);
    }

    /**
     * Creates a new EventBus with the given {@link SubscriberExceptionHandler}.
     * @param exceptionHandler Handler for subscriber exceptions.
     * @since 16.0
     */
    public EventBus(SubscriberExceptionHandler exceptionHandler) {
        this("default", MoreExecutors.directExecutor(),Dispatcher.perThreadDispatchQueue(),exceptionHandler);
    }

    EventBus( String identifier, Executor executor, Dispatcher dispatcher, SubscriberExceptionHandler exceptionHandler) {
        this.identifier = checkNotNull(identifier);
        this.executor = checkNotNull(executor);
        this.dispatcher = checkNotNull(dispatcher);
        this.exceptionHandler = checkNotNull(exceptionHandler);
    }

    /**
     * Returns the identifier for this event bus.
     * @since 19.0
     */
    public final String identifier() {
        return identifier;
    }

    /** Returns the default executor this event bus uses for dispatching events to subscribers. */
    final Executor executor() {
        return executor;
    }

    /** Handles the given exception thrown by a subscriber with the given context. */
    void handleSubscriberException(Throwable e, SubscriberExceptionContext context) {
        checkNotNull(e);
        checkNotNull(context);
        try {
            exceptionHandler.handleException(e, context);
        } catch (Throwable e2) {
            // if the handler threw an exception... well, just log it
            logger.log( Level.SEVERE, String.format(Locale.ROOT, "Exception %s thrown while handling exception: %s", e2, e),e2);
        }
    }

    /** 注册 listener
     * Registers all subscriber methods on {@code object} to receive events.
     * @param object object whose subscriber methods should be registered.
     */
    public void register(Object object) {
        subscribers.register(object);
    }

    /**  取消注册 listener
     * Unregisters all subscriber methods on a registered {@code object}.
     * @param object object whose subscriber methods should be unregistered.
     * @throws IllegalArgumentException if the object was not previously registered.
     */
    public void unregister(Object object) {
        subscribers.unregister(object);
    }

    /**  消息广播：  这块主要是根据event事件类型，来获取事件的订阅者，然后进行事件消息的分发。当然，如果没有订阅者，也就是event的类型是DeadEvent，也会进行对应的处理。
     * Posts an event to all registered subscribers. This method will return successfully after the
     * event has been posted to all subscribers, and regardless of any exceptions thrown by
     * subscribers.
     * <p>If no subscribers have been subscribed for {@code event}'s class, and {@code event} is not
     * already a {@link DeadEvent}, it will be wrapped in a DeadEvent and reposted.
     * @param event event to post.
     */
    public void post(Object event) {
        //SubscriberRegistry根据事件取出对应的订阅者
        Iterator<Subscriber> eventSubscribers = subscribers.getSubscribers(event);
        if (eventSubscribers.hasNext()) {
            //交给对应的Dispatcher处理
            dispatcher.dispatch(event, eventSubscribers);
        } else if (!(event instanceof DeadEvent)) {
            // the event had no subscribers and was not itself a DeadEvent
            post(new DeadEvent(this, event));
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(identifier).toString();
    }

    /** Simple logging handler for subscriber exceptions. */
    static final class LoggingHandler implements SubscriberExceptionHandler {

        static final LoggingHandler INSTANCE = new LoggingHandler();

        @Override
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            Logger logger = logger(context);
            if (logger.isLoggable(Level.SEVERE)) {
                logger.log(Level.SEVERE, message(context), exception);
            }
        }

        private static Logger logger(SubscriberExceptionContext context) {
            return Logger.getLogger(EventBus.class.getName() + "." + context.getEventBus().identifier());
        }

        private static String message(SubscriberExceptionContext context) {
            Method method = context.getSubscriberMethod();
            return "Exception thrown by subscriber method " + method.getName() + '(' + method.getParameterTypes()[0].getName() + ')'
                    + " on subscriber "  + context.getSubscriber() + " when dispatching event: "  + context.getEvent();
        }
    }
}
