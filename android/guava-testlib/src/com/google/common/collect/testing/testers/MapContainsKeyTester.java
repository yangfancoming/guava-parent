

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionSize.ZERO;
import static com.google.common.collect.testing.features.MapFeature.ALLOWS_NULL_KEYS;
import static com.google.common.collect.testing.features.MapFeature.ALLOWS_NULL_KEY_QUERIES;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractMapTester;
import com.google.common.collect.testing.WrongType;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.MapFeature;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code containsKey()} operations on a map. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.MapTestSuiteBuilder}.
 *
 * @author George van den Driessche
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MapContainsKeyTester<K, V> extends AbstractMapTester<K, V> {
  @CollectionSize.Require(absent = ZERO)
  public void testContains_yes() {
    assertTrue("containsKey(present) should return true", getMap().containsKey(k0()));
  }

  public void testContains_no() {
    assertFalse("containsKey(notPresent) should return false", getMap().containsKey(k3()));
  }

  @MapFeature.Require(ALLOWS_NULL_KEY_QUERIES)
  public void testContains_nullNotContainedButAllowed() {
    assertFalse("containsKey(null) should return false", getMap().containsKey(null));
  }

  @MapFeature.Require(absent = ALLOWS_NULL_KEY_QUERIES)
  public void testContains_nullNotContainedAndUnsupported() {
    expectNullKeyMissingWhenNullKeysUnsupported("containsKey(null) should return false or throw");
  }

  @MapFeature.Require(ALLOWS_NULL_KEYS)
  @CollectionSize.Require(absent = ZERO)
  public void testContains_nonNullWhenNullContained() {
    initMapWithNullKey();
    assertFalse("containsKey(notPresent) should return false", getMap().containsKey(k3()));
  }

  @MapFeature.Require(ALLOWS_NULL_KEYS)
  @CollectionSize.Require(absent = ZERO)
  public void testContains_nullContained() {
    initMapWithNullKey();
    assertTrue("containsKey(null) should return true", getMap().containsKey(null));
  }

  public void testContains_wrongType() {
    try {
      // noinspection SuspiciousMethodCalls
      assertFalse(
          "containsKey(wrongType) should return false or throw",
          getMap().containsKey(WrongType.VALUE));
    } catch (ClassCastException tolerated) {
    }
  }
}
