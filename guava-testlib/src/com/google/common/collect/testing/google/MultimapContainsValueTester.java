package com.google.common.collect.testing.google;

import static com.google.common.collect.testing.features.CollectionSize.ZERO;
import static com.google.common.collect.testing.features.MapFeature.ALLOWS_NULL_VALUES;
import static com.google.common.collect.testing.features.MapFeature.ALLOWS_NULL_VALUE_QUERIES;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multimap;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.MapFeature;
import org.junit.Ignore;

/**
 * Tester for {@link Multimap#containsValue}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MultimapContainsValueTester<K, V>
    extends AbstractMultimapTester<K, V, Multimap<K, V>> {
  @CollectionSize.Require(absent = ZERO)
  public void testContainsValueYes() {
    assertTrue(multimap().containsValue(v0()));
  }

  public void testContainsValueNo() {
    assertFalse(multimap().containsValue(v3()));
  }

  @MapFeature.Require(ALLOWS_NULL_VALUES)
  @CollectionSize.Require(absent = ZERO)
  public void testContainsNullValueYes() {
    initMultimapWithNullValue();
    assertTrue(multimap().containsValue(null));
  }

  @MapFeature.Require(ALLOWS_NULL_VALUE_QUERIES)
  public void testContainsNullValueNo() {
    assertFalse(multimap().containsValue(null));
  }

  @MapFeature.Require(absent = ALLOWS_NULL_VALUE_QUERIES)
  public void testContainsNullValueFails() {
    try {
      multimap().containsValue(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
      // success
    }
  }
}
