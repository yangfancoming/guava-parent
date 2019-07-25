package com.google.common.collect.testing.google;

import static com.google.common.collect.testing.features.CollectionSize.ZERO;
import static com.google.common.collect.testing.features.MapFeature.ALLOWS_NULL_KEYS;
import static com.google.common.collect.testing.features.MapFeature.ALLOWS_NULL_KEY_QUERIES;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multimap;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.MapFeature;
import org.junit.Ignore;

/**
 * Tester for the {@code containsKey} methods of {@code Multimap} and its {@code asMap()} view.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MultimapContainsKeyTester<K, V> extends AbstractMultimapTester<K, V, Multimap<K, V>> {
  @CollectionSize.Require(absent = ZERO)
  public void testContainsKeyYes() {
    assertTrue(multimap().containsKey(k0()));
  }

  public void testContainsKeyNo() {
    assertFalse(multimap().containsKey(k3()));
  }

  public void testContainsKeysFromKeySet() {
    for (K k : multimap().keySet()) {
      assertTrue(multimap().containsKey(k));
    }
  }

  public void testContainsKeyAgreesWithGet() {
    for (K k : sampleKeys()) {
      assertEquals(!multimap().get(k).isEmpty(), multimap().containsKey(k));
    }
  }

  public void testContainsKeyAgreesWithAsMap() {
    for (K k : sampleKeys()) {
      assertEquals(multimap().containsKey(k), multimap().asMap().containsKey(k));
    }
  }

  public void testContainsKeyAgreesWithKeySet() {
    for (K k : sampleKeys()) {
      assertEquals(multimap().containsKey(k), multimap().keySet().contains(k));
    }
  }

  @MapFeature.Require(ALLOWS_NULL_KEYS)
  @CollectionSize.Require(absent = ZERO)
  public void testContainsKeyNullPresent() {
    initMultimapWithNullKey();
    assertTrue(multimap().containsKey(null));
  }

  @MapFeature.Require(ALLOWS_NULL_KEY_QUERIES)
  public void testContainsKeyNullAbsent() {
    assertFalse(multimap().containsKey(null));
  }

  @MapFeature.Require(absent = ALLOWS_NULL_KEY_QUERIES)
  public void testContainsKeyNullDisallowed() {
    try {
      multimap().containsKey(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
      // success
    }
  }
}