package com.google.common.collect.testing.google;

import com.google.common.annotations.GwtCompatible;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests unconditional {@code setCount()} operations on a multiset. Can't
 * be invoked directly; please see {@link MultisetTestSuiteBuilder}.
 *
 * @author Chris Povirk
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MultisetSetCountUnconditionallyTester<E> extends AbstractMultisetSetCountTester<E> {
  @Override
  void setCountCheckReturnValue(E element, int count) {
    assertEquals(
        "multiset.setCount() should return the old count",
        getMultiset().count(element),
        setCount(element, count));
  }

  @Override
  void setCountNoCheckReturnValue(E element, int count) {
    setCount(element, count);
  }

  private int setCount(E element, int count) {
    return getMultiset().setCount(element, count);
  }
}
