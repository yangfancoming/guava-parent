

package com.google.common.collect.testing.testers;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.testing.Helpers;
import java.lang.reflect.Method;
import org.junit.Ignore;

/**
 * Tests {@link java.util.List#hashCode}.
 *
 * @author George van den Driessche
 */
@GwtCompatible(emulated = true)
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class ListHashCodeTester<E> extends AbstractListTester<E> {
  public void testHashCode() {
    int expectedHashCode = 1;
    for (E element : getOrderedElements()) {
      expectedHashCode = 31 * expectedHashCode + ((element == null) ? 0 : element.hashCode());
    }
    assertEquals(
        "A List's hashCode() should be computed from those of its elements.",
        expectedHashCode,
        getList().hashCode());
  }

  /**
   * Returns the {@link Method} instance for {@link #testHashCode()} so that list tests on
   * unhashable objects can suppress it with {@code FeatureSpecificTestSuiteBuilder.suppressing()}.
   */
  @GwtIncompatible // reflection
  public static Method getHashCodeMethod() {
    return Helpers.getMethod(ListHashCodeTester.class, "testHashCode");
  }
}
