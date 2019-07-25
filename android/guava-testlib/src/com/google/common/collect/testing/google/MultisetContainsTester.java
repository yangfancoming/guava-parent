

package com.google.common.collect.testing.google;

import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.features.CollectionSize;
import java.util.Arrays;
import org.junit.Ignore;

/**
 * Tests for {@code Multiset.containsAll} not already addressed by {@code CollectionContainsTester}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MultisetContainsTester<E> extends AbstractMultisetTester<E> {
  @CollectionSize.Require(absent = ZERO)
  public void testContainsAllMultisetIgnoresFrequency() {
    assertTrue(getMultiset().containsAll(getSubjectGenerator().create(e0(), e0(), e0())));
  }

  @CollectionSize.Require(absent = ZERO)
  public void testContainsAllListIgnoresFrequency() {
    assertTrue(getMultiset().containsAll(Arrays.asList(e0(), e0(), e0())));
  }
}
