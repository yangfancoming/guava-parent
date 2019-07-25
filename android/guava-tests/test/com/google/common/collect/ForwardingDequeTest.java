package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.testing.ForwardingWrapperTester;
import java.util.Deque;
import junit.framework.TestCase;

/**
 * Tests for {@code ForwardingDeque}.
 *
 * @author Kurt Alfred Kluever
 */
public class ForwardingDequeTest extends TestCase {

  @SuppressWarnings("rawtypes")
  public void testForwarding() {
    new ForwardingWrapperTester()
        .testForwarding(
            Deque.class,
            new Function<Deque, Deque>() {
              @Override
              public Deque apply(Deque delegate) {
                return wrap(delegate);
              }
            });
  }

  private static <T> Deque<T> wrap(final Deque<T> delegate) {
    return new ForwardingDeque<T>() {
      @Override
      protected Deque<T> delegate() {
        return delegate;
      }
    };
  }
}
