

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.SUPPORTS_REMOVE;
import static com.google.common.collect.testing.features.CollectionSize.ONE;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.MinimalCollection;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests removeAll operations on a list. Can't be invoked directly;
 * please see {@link com.google.common.collect.testing.ListTestSuiteBuilder}.
 *
 * @author George van den Driessche
 */
@SuppressWarnings("unchecked") // too many "unchecked generic array creations"
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class ListRemoveAllTester<E> extends AbstractListTester<E> {
  @CollectionFeature.Require(SUPPORTS_REMOVE)
  @CollectionSize.Require(absent = {ZERO, ONE})
  public void testRemoveAll_duplicate() {
    ArrayWithDuplicate<E> arrayAndDuplicate = createArrayWithDuplicateElement();
    collection = getSubjectGenerator().create(arrayAndDuplicate.elements);
    E duplicate = arrayAndDuplicate.duplicate;

    assertTrue(
        "removeAll(intersectingCollection) should return true",
        getList().removeAll(MinimalCollection.of(duplicate)));
    assertFalse(
        "after removeAll(e), a collection should not contain e even "
            + "if it initially contained e more than once.",
        getList().contains(duplicate));
  }

  // All other cases are covered by CollectionRemoveAllTester.
}
