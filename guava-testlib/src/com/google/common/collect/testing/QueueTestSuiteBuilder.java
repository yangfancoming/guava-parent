

package com.google.common.collect.testing;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.testing.testers.QueueElementTester;
import com.google.common.collect.testing.testers.QueueOfferTester;
import com.google.common.collect.testing.testers.QueuePeekTester;
import com.google.common.collect.testing.testers.QueuePollTester;
import com.google.common.collect.testing.testers.QueueRemoveTester;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates, based on your criteria, a JUnit test suite that exhaustively tests a queue
 * implementation.
 *
 * @author Jared Levy
 */
@GwtIncompatible
public final class QueueTestSuiteBuilder<E>
    extends AbstractCollectionTestSuiteBuilder<QueueTestSuiteBuilder<E>, E> {
  public static <E> QueueTestSuiteBuilder<E> using(TestQueueGenerator<E> generator) {
    return new QueueTestSuiteBuilder<E>().usingGenerator(generator);
  }

  private boolean runCollectionTests = true;

  /**
   * Specify whether to skip the general collection tests. Call this method when testing a
   * collection that's both a queue and a list, to avoid running the common collection tests twice.
   * By default, collection tests do run.
   */
  public QueueTestSuiteBuilder<E> skipCollectionTests() {
    runCollectionTests = false;
    return this;
  }

  @Override
  protected List<Class<? extends AbstractTester>> getTesters() {
    List<Class<? extends AbstractTester>> testers = new ArrayList<>();
    if (runCollectionTests) {
      testers.addAll(super.getTesters());
    }

    testers.add(QueueElementTester.class);
    testers.add(QueueOfferTester.class);
    testers.add(QueuePeekTester.class);
    testers.add(QueuePollTester.class);
    testers.add(QueueRemoveTester.class);
    return testers;
  }
}
