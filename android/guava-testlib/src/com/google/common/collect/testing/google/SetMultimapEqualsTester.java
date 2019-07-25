package com.google.common.collect.testing.google;

import static com.google.common.collect.testing.features.CollectionSize.SEVERAL;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.testing.Helpers;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.testing.EqualsTester;
import org.junit.Ignore;

/**
 * Testers for {@link SetMultimap#equals(Object)}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class SetMultimapEqualsTester<K, V> extends AbstractMultimapTester<K, V, SetMultimap<K, V>> {
  @CollectionSize.Require(SEVERAL)
  public void testOrderingDoesntAffectEqualsComparisons() {
    SetMultimap<K, V> multimap1 =
        getSubjectGenerator()
            .create(
                Helpers.mapEntry(k0(), v0()),
                Helpers.mapEntry(k0(), v1()),
                Helpers.mapEntry(k0(), v4()));
    SetMultimap<K, V> multimap2 =
        getSubjectGenerator()
            .create(
                Helpers.mapEntry(k0(), v1()),
                Helpers.mapEntry(k0(), v0()),
                Helpers.mapEntry(k0(), v4()));
    new EqualsTester().addEqualityGroup(multimap1, multimap2).testEquals();
  }
}
