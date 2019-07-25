

package com.google.common.collect.testing.testers;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractCollectionTester;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code size()} operations on a collection. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.CollectionTestSuiteBuilder}.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class CollectionSizeTester<E> extends AbstractCollectionTester<E> {
  public void testSize() {
    assertEquals("size():", getNumElements(), collection.size());
  }
}
