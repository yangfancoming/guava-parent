

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.KNOWN_ORDER;
import static com.google.common.collect.testing.features.CollectionSize.ONE;
import static com.google.common.collect.testing.features.CollectionSize.SEVERAL;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import java.util.NoSuchElementException;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code element()} operations on a queue. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.CollectionTestSuiteBuilder}.
 *
 * @author Jared Levy
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class QueueElementTester<E> extends AbstractQueueTester<E> {
  @CollectionSize.Require(ZERO)
  public void testElement_empty() {
    try {
      getQueue().element();
      fail("emptyQueue.element() should throw");
    } catch (NoSuchElementException expected) {
    }
    expectUnchanged();
  }

  @CollectionSize.Require(ONE)
  public void testElement_size1() {
    assertEquals("size1Queue.element() should return first element", e0(), getQueue().element());
    expectUnchanged();
  }

  @CollectionFeature.Require(KNOWN_ORDER)
  @CollectionSize.Require(SEVERAL)
  public void testElement_sizeMany() {
    assertEquals("sizeManyQueue.element() should return first element", e0(), getQueue().element());
    expectUnchanged();
  }
}
