

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.KNOWN_ORDER;
import static com.google.common.collect.testing.features.CollectionFeature.SUPPORTS_REMOVE;
import static com.google.common.collect.testing.features.CollectionSize.ONE;
import static com.google.common.collect.testing.features.CollectionSize.SEVERAL;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import java.util.NoSuchElementException;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code remove()} operations on a queue. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.CollectionTestSuiteBuilder}.
 *
 * @author Jared Levy
 */
@SuppressWarnings("unchecked") // too many "unchecked generic array creations"
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class QueueRemoveTester<E> extends AbstractQueueTester<E> {
  @CollectionFeature.Require(SUPPORTS_REMOVE)
  @CollectionSize.Require(ZERO)
  public void testRemove_empty() {
    try {
      getQueue().remove();
      fail("emptyQueue.remove() should throw");
    } catch (NoSuchElementException expected) {
    }
    expectUnchanged();
  }

  @CollectionFeature.Require(SUPPORTS_REMOVE)
  @CollectionSize.Require(ONE)
  public void testRemove_size1() {
    assertEquals("size1Queue.remove() should return first element", e0(), getQueue().remove());
    expectMissing(e0());
  }

  @CollectionFeature.Require({KNOWN_ORDER, SUPPORTS_REMOVE})
  @CollectionSize.Require(SEVERAL)
  public void testRemove_sizeMany() {
    assertEquals("sizeManyQueue.remove() should return first element", e0(), getQueue().remove());
    expectMissing(e0());
  }
}
