

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.ALLOWS_NULL_VALUES;
import static com.google.common.collect.testing.features.CollectionFeature.SUPPORTS_ADD;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.features.CollectionFeature;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests offer operations on a queue. Can't be invoked directly; please
 * see {@link com.google.common.collect.testing.CollectionTestSuiteBuilder}.
 *
 * @author Jared Levy
 */
@SuppressWarnings("unchecked") // too many "unchecked generic array creations"
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class QueueOfferTester<E> extends AbstractQueueTester<E> {
  @CollectionFeature.Require(SUPPORTS_ADD)
  public void testOffer_supportedNotPresent() {
    assertTrue("offer(notPresent) should return true", getQueue().offer(e3()));
    expectAdded(e3());
  }

  @CollectionFeature.Require({SUPPORTS_ADD, ALLOWS_NULL_VALUES})
  public void testOffer_nullSupported() {
    assertTrue("offer(null) should return true", getQueue().offer(null));
    expectAdded((E) null);
  }

  @CollectionFeature.Require(value = SUPPORTS_ADD, absent = ALLOWS_NULL_VALUES)
  public void testOffer_nullUnsupported() {
    try {
      getQueue().offer(null);
      fail("offer(null) should throw");
    } catch (NullPointerException expected) {
    }
    expectUnchanged();
    expectNullMissingWhenNullUnsupported("Should not contain null after unsupported offer(null)");
  }
}
