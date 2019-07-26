

package com.google.common.collect.testing.testers;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractMapTester;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code size()} operations on a map. Can't be invoked directly;
 * please see {@link com.google.common.collect.testing.MapTestSuiteBuilder}.
 *
 * @author George van den Driessche
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MapSizeTester<K, V> extends AbstractMapTester<K, V> {
  public void testSize() {
    assertEquals("size():", getNumElements(), getMap().size());
  }
}
