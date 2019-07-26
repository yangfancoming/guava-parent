

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.SUPPORTS_REMOVE;
import static com.google.common.collect.testing.features.CollectionSize.ONE;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code remove(Object)} operations on a list. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.ListTestSuiteBuilder}.
 *
 * @author George van den Driessche
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class ListRemoveTester<E> extends AbstractListTester<E> {
  @CollectionFeature.Require(SUPPORTS_REMOVE)
  @CollectionSize.Require(absent = {ZERO, ONE})
  public void testRemove_duplicate() {
    ArrayWithDuplicate<E> arrayAndDuplicate = createArrayWithDuplicateElement();
    collection = getSubjectGenerator().create(arrayAndDuplicate.elements);
    E duplicate = arrayAndDuplicate.duplicate;

    int firstIndex = getList().indexOf(duplicate);
    int initialSize = getList().size();
    assertTrue("remove(present) should return true", getList().remove(duplicate));
    assertTrue(
        "After remove(duplicate), a list should still contain the duplicate element",
        getList().contains(duplicate));
    assertFalse(
        "remove(duplicate) should remove the first instance of the "
            + "duplicate element in the list",
        firstIndex == getList().indexOf(duplicate));
    assertEquals(
        "remove(present) should decrease the size of a list by one.",
        initialSize - 1,
        getList().size());
  }
}
