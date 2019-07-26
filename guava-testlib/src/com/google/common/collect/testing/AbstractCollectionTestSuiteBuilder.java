

package com.google.common.collect.testing;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.testing.testers.CollectionAddAllTester;
import com.google.common.collect.testing.testers.CollectionAddTester;
import com.google.common.collect.testing.testers.CollectionClearTester;
import com.google.common.collect.testing.testers.CollectionContainsAllTester;
import com.google.common.collect.testing.testers.CollectionContainsTester;
import com.google.common.collect.testing.testers.CollectionCreationTester;
import com.google.common.collect.testing.testers.CollectionEqualsTester;
import com.google.common.collect.testing.testers.CollectionForEachTester;
import com.google.common.collect.testing.testers.CollectionIsEmptyTester;
import com.google.common.collect.testing.testers.CollectionIteratorTester;
import com.google.common.collect.testing.testers.CollectionRemoveAllTester;
import com.google.common.collect.testing.testers.CollectionRemoveIfTester;
import com.google.common.collect.testing.testers.CollectionRemoveTester;
import com.google.common.collect.testing.testers.CollectionRetainAllTester;
import com.google.common.collect.testing.testers.CollectionSerializationTester;
import com.google.common.collect.testing.testers.CollectionSizeTester;
import com.google.common.collect.testing.testers.CollectionSpliteratorTester;
import com.google.common.collect.testing.testers.CollectionStreamTester;
import com.google.common.collect.testing.testers.CollectionToArrayTester;
import com.google.common.collect.testing.testers.CollectionToStringTester;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Abstract superclass of all test-suite builders for collection interfaces.
 *
 * @author George van den Driessche
 */
@GwtIncompatible
public abstract class AbstractCollectionTestSuiteBuilder<
        B extends AbstractCollectionTestSuiteBuilder<B, E>, E>
    extends PerCollectionSizeTestSuiteBuilder<B, TestCollectionGenerator<E>, Collection<E>, E> {
  // Class parameters must be raw.
  @SuppressWarnings("unchecked")
  @Override
  protected List<Class<? extends AbstractTester>> getTesters() {
    return Arrays.<Class<? extends AbstractTester>>asList(
        CollectionAddAllTester.class,
        CollectionAddTester.class,
        CollectionClearTester.class,
        CollectionContainsAllTester.class,
        CollectionContainsTester.class,
        CollectionCreationTester.class,
        CollectionEqualsTester.class,
        CollectionForEachTester.class,
        CollectionIsEmptyTester.class,
        CollectionIteratorTester.class,
        CollectionRemoveAllTester.class,
        CollectionRemoveIfTester.class,
        CollectionRemoveTester.class,
        CollectionRetainAllTester.class,
        CollectionSerializationTester.class,
        CollectionSizeTester.class,
        CollectionSpliteratorTester.class,
        CollectionStreamTester.class,
        CollectionToArrayTester.class,
        CollectionToStringTester.class);
  }
}
