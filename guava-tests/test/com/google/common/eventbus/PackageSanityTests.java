package com.google.common.eventbus;

import com.google.common.testing.AbstractPackageSanityTests;
import java.lang.reflect.Method;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Basic sanity tests for the entire package.
 *
 * @author Ben Yu
 */

public class PackageSanityTests extends AbstractPackageSanityTests {

  public PackageSanityTests() throws Exception {
    DummySubscriber dummySubscriber = new DummySubscriber();
    setDefault(Subscriber.class, dummySubscriber.toSubscriber());
    setDefault(Method.class, DummySubscriber.subscriberMethod());
    setDefault(SubscriberExceptionContext.class, dummySubscriber.toContext());
    setDefault(Dispatcher.class, Dispatcher.immediate());
  }

  private static class DummySubscriber {

    private final EventBus eventBus = new EventBus();

    @Subscribe
    public void handle(@Nullable Object anything) {}

    Subscriber toSubscriber() throws Exception {
      return Subscriber.create(eventBus, this, subscriberMethod());
    }

    SubscriberExceptionContext toContext() {
      return new SubscriberExceptionContext(eventBus, new Object(), this, subscriberMethod());
    }

    private static Method subscriberMethod() {
      try {
        return DummySubscriber.class.getMethod("handle", Object.class);
      } catch (NoSuchMethodException e) {
        throw new AssertionError(e);
      }
    }
  }
}
