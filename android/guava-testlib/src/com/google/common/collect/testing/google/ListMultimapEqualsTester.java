

package com.google.common.collect.testing.google;

import static com.google.common.collect.testing.features.CollectionSize.SEVERAL;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.testing.Helpers;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.testing.EqualsTester;
import org.junit.Ignore;

/**
 * Testers for {@link ListMultimap#equals(Object)}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class ListMultimapEqualsTester<K, V> extends AbstractListMultimapTester<K, V> {
  @CollectionSize.Require(SEVERAL)
  public void testOrderingAffectsEqualsComparisons() {
    ListMultimap<K, V> multimap1 =
        getSubjectGenerator()
            .create(
                Helpers.mapEntry(k0(), v0()),
                Helpers.mapEntry(k0(), v1()),
                Helpers.mapEntry(k0(), v0()));
    ListMultimap<K, V> multimap2 =
        getSubjectGenerator()
            .create(
                Helpers.mapEntry(k0(), v1()),
                Helpers.mapEntry(k0(), v0()),
                Helpers.mapEntry(k0(), v0()));
    new EqualsTester().addEqualityGroup(multimap1).addEqualityGroup(multimap2).testEquals();
  }
}
