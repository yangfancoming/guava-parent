

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractMapTester;
import com.google.common.collect.testing.features.CollectionSize;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code isEmpty()} operations on a map. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.MapTestSuiteBuilder}.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MapIsEmptyTester<K, V> extends AbstractMapTester<K, V> {
  @CollectionSize.Require(ZERO)
  public void testIsEmpty_yes() {
    assertTrue("isEmpty() should return true", getMap().isEmpty());
  }

  @CollectionSize.Require(absent = ZERO)
  public void testIsEmpty_no() {
    assertFalse("isEmpty() should return false", getMap().isEmpty());
  }
}
