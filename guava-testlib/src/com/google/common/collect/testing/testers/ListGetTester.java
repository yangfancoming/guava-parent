

package com.google.common.collect.testing.testers;

import com.google.common.annotations.GwtCompatible;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code get()} operations on a list. Can't be invoked directly;
 * please see {@link com.google.common.collect.testing.ListTestSuiteBuilder}.
 *
 * @author Chris Povirk
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class ListGetTester<E> extends AbstractListTester<E> {
  public void testGet_valid() {
    // This calls get() on each index and checks the result:
    expectContents(createOrderedArray());
  }

  public void testGet_negative() {
    try {
      getList().get(-1);
      fail("get(-1) should throw");
    } catch (IndexOutOfBoundsException expected) {
    }
  }

  public void testGet_tooLarge() {
    try {
      getList().get(getNumElements());
      fail("get(size) should throw");
    } catch (IndexOutOfBoundsException expected) {
    }
  }
}
