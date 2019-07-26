

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.FAILS_FAST_ON_CONCURRENT_MODIFICATION;
import static com.google.common.collect.testing.features.CollectionSize.ONE;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;
import static com.google.common.collect.testing.features.ListFeature.SUPPORTS_REMOVE_WITH_INDEX;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.Helpers;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code remove(int)} operations on a list. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.ListTestSuiteBuilder}.
 *
 * @author Chris Povirk
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class ListRemoveAtIndexTester<E> extends AbstractListTester<E> {
  @ListFeature.Require(absent = SUPPORTS_REMOVE_WITH_INDEX)
  @CollectionSize.Require(absent = ZERO)
  public void testRemoveAtIndex_unsupported() {
    try {
      getList().remove(0);
      fail("remove(i) should throw");
    } catch (UnsupportedOperationException expected) {
    }
    expectUnchanged();
  }

  @ListFeature.Require(SUPPORTS_REMOVE_WITH_INDEX)
  public void testRemoveAtIndex_negative() {
    try {
      getList().remove(-1);
      fail("remove(-1) should throw");
    } catch (IndexOutOfBoundsException expected) {
    }
    expectUnchanged();
  }

  @ListFeature.Require(SUPPORTS_REMOVE_WITH_INDEX)
  public void testRemoveAtIndex_tooLarge() {
    try {
      getList().remove(getNumElements());
      fail("remove(size) should throw");
    } catch (IndexOutOfBoundsException expected) {
    }
    expectUnchanged();
  }

  @ListFeature.Require(SUPPORTS_REMOVE_WITH_INDEX)
  @CollectionSize.Require(absent = ZERO)
  public void testRemoveAtIndex_first() {
    runRemoveTest(0);
  }

  @ListFeature.Require(SUPPORTS_REMOVE_WITH_INDEX)
  @CollectionSize.Require(absent = {ZERO, ONE})
  public void testRemoveAtIndex_middle() {
    runRemoveTest(getNumElements() / 2);
  }

  @CollectionFeature.Require(FAILS_FAST_ON_CONCURRENT_MODIFICATION)
  @ListFeature.Require(SUPPORTS_REMOVE_WITH_INDEX)
  @CollectionSize.Require(absent = ZERO)
  public void testRemoveAtIndexConcurrentWithIteration() {
    try {
      Iterator<E> iterator = collection.iterator();
      getList().remove(getNumElements() / 2);
      iterator.next();
      fail("Expected ConcurrentModificationException");
    } catch (ConcurrentModificationException expected) {
      // success
    }
  }

  @ListFeature.Require(SUPPORTS_REMOVE_WITH_INDEX)
  @CollectionSize.Require(absent = ZERO)
  public void testRemoveAtIndex_last() {
    runRemoveTest(getNumElements() - 1);
  }

  private void runRemoveTest(int index) {
    assertEquals(
        Platform.format("remove(%d) should return the element at index %d", index, index),
        getList().get(index),
        getList().remove(index));
    List<E> expected = Helpers.copyToList(createSamplesArray());
    expected.remove(index);
    expectContents(expected);
  }
}
