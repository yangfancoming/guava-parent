

package com.google.common.collect.testing.google;

import static com.google.common.collect.testing.features.CollectionFeature.NON_STANDARD_TOSTRING;
import static com.google.common.collect.testing.features.CollectionSize.ONE;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;
import static com.google.common.collect.testing.features.MapFeature.ALLOWS_NULL_KEYS;
import static com.google.common.collect.testing.features.MapFeature.ALLOWS_NULL_VALUES;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multimap;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.MapFeature;
import org.junit.Ignore;

/**
 * Tester for {@code Multimap.toString()}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MultimapToStringTester<K, V> extends AbstractMultimapTester<K, V, Multimap<K, V>> {
  @CollectionSize.Require(ZERO)
  @CollectionFeature.Require(absent = NON_STANDARD_TOSTRING)
  public void testToStringEmpty() {
    assertEquals("{}", multimap().toString());
  }

  @CollectionSize.Require(ONE)
  @CollectionFeature.Require(absent = NON_STANDARD_TOSTRING)
  public void testToStringSingleton() {
    assertEquals("{" + k0() + "=[" + v0() + "]}", multimap().toString());
  }

  @CollectionSize.Require(absent = ZERO)
  @CollectionFeature.Require(absent = NON_STANDARD_TOSTRING)
  @MapFeature.Require(ALLOWS_NULL_KEYS)
  public void testToStringWithNullKey() {
    initMultimapWithNullKey();
    testToStringMatchesAsMap();
  }

  @CollectionSize.Require(absent = ZERO)
  @CollectionFeature.Require(absent = NON_STANDARD_TOSTRING)
  @MapFeature.Require(ALLOWS_NULL_VALUES)
  public void testToStringWithNullValue() {
    initMultimapWithNullValue();
    testToStringMatchesAsMap();
  }

  @CollectionFeature.Require(absent = NON_STANDARD_TOSTRING)
  public void testToStringMatchesAsMap() {
    assertEquals(multimap().asMap().toString(), multimap().toString());
  }
}
