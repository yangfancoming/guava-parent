

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.features.CollectionSize;
import java.util.Arrays;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code toArray()} operations on a list. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.ListTestSuiteBuilder}.
 *
 * @author Chris Povirk
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class ListToArrayTester<E> extends AbstractListTester<E> {
  // CollectionToArrayTester tests everything except ordering.

  public void testToArray_noArg() {
    Object[] actual = getList().toArray();
    assertArrayEquals("toArray() order should match list", createOrderedArray(), actual);
  }

  @CollectionSize.Require(absent = ZERO)
  public void testToArray_tooSmall() {
    Object[] actual = getList().toArray(new Object[0]);
    assertArrayEquals("toArray(tooSmall) order should match list", createOrderedArray(), actual);
  }

  public void testToArray_largeEnough() {
    Object[] actual = getList().toArray(new Object[getNumElements()]);
    assertArrayEquals("toArray(largeEnough) order should match list", createOrderedArray(), actual);
  }

  private static void assertArrayEquals(String message, Object[] expected, Object[] actual) {
    assertEquals(message, Arrays.asList(expected), Arrays.asList(actual));
  }
}
