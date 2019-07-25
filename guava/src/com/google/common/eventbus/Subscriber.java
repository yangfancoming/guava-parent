

package com.google.common.eventbus;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.VisibleForTesting;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A subscriber method on a specific object, plus the executor that should be used for dispatching events to it.
 * <p>Two subscribers are equivalent when they refer to the same method on the same object (not
 * class). This property is used to ensure that no subscriber method is registered more than once.
 */
class Subscriber {


  /** Creates a {@code Subscriber} for {@code method} on {@code listener}. */
  /**
   *  这个类只是package内可见，没有定义为public，可以通过静态方法create来创建它。
   * 这里传入的method就是使用了@Subscribe注解的方法，这块会先判断这个方法是否线程安全，
   * 即是否使用@AllowConcurrentEvent来进行注解，来创建不同的Subscriber。
   * 唯一的差别是SynchronizedSubscriber中一个方法使用了synchronized来修饰
  */
  static Subscriber create(EventBus bus, Object listener, Method method) {
    return isDeclaredThreadSafe(method) ? new Subscriber(bus, listener, method) : new SynchronizedSubscriber(bus, listener, method);
  }

  /** The event bus this subscriber belongs to. */
  @Weak private EventBus bus;

  /** The object with the subscriber method. */
  @VisibleForTesting final Object target;

  /** Subscriber method. */
  private final Method method;

  /** Executor to use for dispatching events to this subscriber. */
  private final Executor executor;

  private Subscriber(EventBus bus, Object target, Method method) {
    this.bus = bus;
    this.target = checkNotNull(target);
    this.method = method;
    method.setAccessible(true);

    this.executor = bus.executor();
  }

  /** Dispatches {@code event} to this subscriber using the proper executor. */
  final void dispatchEvent(final Object event) {
      // 调用多线程来处理event。
    executor.execute(()->{
      try {
        invokeSubscriberMethod(event);
      } catch (InvocationTargetException e) {
        bus.handleSubscriberException(e.getCause(), context(event));
      }
    });
  }

  /**
   * Invokes the subscriber method. This method can be overridden to make the invocation synchronized.
   */
  @VisibleForTesting
  void invokeSubscriberMethod(Object event) throws InvocationTargetException {
    try {
      method.invoke(target, checkNotNull(event));
    } catch (IllegalArgumentException e) {
      throw new Error("Method rejected target/argument: " + event, e);
    } catch (IllegalAccessException e) {
      throw new Error("Method became inaccessible: " + event, e);
    } catch (InvocationTargetException e) {
      if (e.getCause() instanceof Error) {
        throw (Error) e.getCause();
      }
      throw e;
    }
  }

  /** Gets the context for the given event. */
  private SubscriberExceptionContext context(Object event) {
    return new SubscriberExceptionContext(bus, event, target, method);
  }

  @Override
  public final int hashCode() {
    return (31 + method.hashCode()) * 31 + System.identityHashCode(target);
  }

  @Override
  public final boolean equals(@Nullable Object obj) {
    if (obj instanceof Subscriber) {
      Subscriber that = (Subscriber) obj;
      // Use == so that different equal instances will still receive events.
      // We only guard against the case that the same object is registered
      // multiple times
      return target == that.target && method.equals(that.method);
    }
    return false;
  }

  /**
   * Checks whether {@code method} is thread-safe, as indicated by the presence of the {@link
   * AllowConcurrentEvents} annotation.
   */
  private static boolean isDeclaredThreadSafe(Method method) {
    return method.getAnnotation(AllowConcurrentEvents.class) != null;
  }

  /**
   * Subscriber that synchronizes invocations of a method to ensure that only one thread may enter
   * the method at a time.
   */
  @VisibleForTesting
  static final class SynchronizedSubscriber extends Subscriber {

    private SynchronizedSubscriber(EventBus bus, Object target, Method method) {
      super(bus, target, method);
    }

    @Override
    void invokeSubscriberMethod(Object event) throws InvocationTargetException {
      synchronized (this) {
        super.invokeSubscriberMethod(event);
      }
    }
  }
}
