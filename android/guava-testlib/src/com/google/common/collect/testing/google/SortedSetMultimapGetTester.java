package com.google.common.collect.testing.google;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.SortedSetMultimap;
import org.junit.Ignore;

/**
 * Tester for {@link SortedSetMultimap#get(Object)}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class SortedSetMultimapGetTester<K, V>
    extends AbstractMultimapTester<K, V, SortedSetMultimap<K, V>> {
  public void testValueComparator() {
    assertEquals(multimap().valueComparator(), multimap().get(k0()).comparator());
  }
}
