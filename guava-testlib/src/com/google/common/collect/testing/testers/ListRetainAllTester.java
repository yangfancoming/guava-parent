

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.Helpers.assertContentsInOrder;
import static com.google.common.collect.testing.features.CollectionFeature.SUPPORTS_REMOVE;
import static com.google.common.collect.testing.features.CollectionSize.ONE;
import static com.google.common.collect.testing.features.CollectionSize.SEVERAL;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.MinimalCollection;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import java.util.Arrays;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code retainAll} operations on a list. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.ListTestSuiteBuilder}.
 *
 * @author Chris Povirk
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class ListRetainAllTester<E> extends AbstractListTester<E> {
  @CollectionFeature.Require(SUPPORTS_REMOVE)
  @CollectionSize.Require(absent = {ZERO, ONE})
  public void testRetainAll_duplicatesKept() {
    E[] array = createSamplesArray();
    array[1] = e0();
    collection = getSubjectGenerator().create(array);
    assertFalse(
        "containsDuplicates.retainAll(superset) should return false",
        collection.retainAll(MinimalCollection.of(createSamplesArray())));
    expectContents(array);
  }

  @SuppressWarnings("unchecked")
  @CollectionFeature.Require(SUPPORTS_REMOVE)
  @CollectionSize.Require(SEVERAL)
  public void testRetainAll_duplicatesRemoved() {
    E[] array = createSamplesArray();
    array[1] = e0();
    collection = getSubjectGenerator().create(array);
    assertTrue(
        "containsDuplicates.retainAll(subset) should return true",
        collection.retainAll(MinimalCollection.of(e2())));
    expectContents(e2());
  }

  @SuppressWarnings("unchecked")
  @CollectionFeature.Require(SUPPORTS_REMOVE)
  @CollectionSize.Require(SEVERAL)
  public void testRetainAll_countIgnored() {
    resetContainer(getSubjectGenerator().create(e0(), e2(), e1(), e0()));
    assertTrue(getList().retainAll(Arrays.asList(e0(), e1())));
    assertContentsInOrder(getList(), e0(), e1(), e0());
  }
}
