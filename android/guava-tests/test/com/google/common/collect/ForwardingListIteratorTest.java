

package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.testing.ForwardingWrapperTester;
import java.util.ListIterator;
import junit.framework.TestCase;

/**
 * Tests for {@code ForwardingListIterator}.
 *
 * @author Robert Konigsberg
 */
public class ForwardingListIteratorTest extends TestCase {

  @SuppressWarnings("rawtypes")
  public void testForwarding() {
    new ForwardingWrapperTester()
        .testForwarding(
            ListIterator.class,
            new Function<ListIterator, ListIterator>() {
              @Override
              public ListIterator apply(ListIterator delegate) {
                return wrap(delegate);
              }
            });
  }

  private static <T> ListIterator<T> wrap(final ListIterator<T> delegate) {
    return new ForwardingListIterator<T>() {
      @Override
      protected ListIterator<T> delegate() {
        return delegate;
      }
    };
  }
}
