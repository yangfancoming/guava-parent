

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.Iterator;

/**
 * A utility for testing an Iterator implementation by comparing its behavior to that of a "known
 * good" reference implementation. In order to accomplish this, it's important to test a great
 * variety of sequences of the {@link Iterator#next}, {@link Iterator#hasNext} and {@link
 * Iterator#remove} operations. This utility takes the brute-force approach of trying <i>all</i>
 * possible sequences of these operations, up to a given number of steps. So, if the caller
 * specifies to use <i>n</i> steps, a total of <i>3^n</i> tests are actually performed.
 *
 * <p>For instance, if <i>steps</i> is 5, one example sequence that will be tested is:
 *
 * <ol>
 *   <li>remove();
 *   <li>hasNext()
 *   <li>hasNext();
 *   <li>remove();
 *   <li>next();
 * </ol>
 *
 * <p>This particular order of operations may be unrealistic, and testing all 3^5 of them may be
 * thought of as overkill; however, it's difficult to determine which proper subset of this massive
 * set would be sufficient to expose any possible bug. Brute force is simpler.
 *
 * <p>To use this class the concrete subclass must implement the {@link
 * IteratorTester#newTargetIterator()} method. This is because it's impossible to test an Iterator
 * without changing its state, so the tester needs a steady supply of fresh Iterators.
 *
 * <p>If your iterator supports modification through {@code remove()}, you may wish to override the
 * verify() method, which is called <em>after</em> each sequence and is guaranteed to be called
 * using the latest values obtained from {@link IteratorTester#newTargetIterator()}.
 *
 * @author Kevin Bourrillion
 * @author Chris Povirk
 */
@GwtCompatible
public abstract class IteratorTester<E> extends AbstractIteratorTester<E, Iterator<E>> {
  /**
   * Creates an IteratorTester.
   *
   * @param steps how many operations to test for each tested pair of iterators
   * @param features the features supported by the iterator
   */
  protected IteratorTester(
      int steps,
      Iterable<? extends IteratorFeature> features,
      Iterable<E> expectedElements,
      KnownOrder knownOrder) {
    super(steps, Collections.<E>singleton(null), features, expectedElements, knownOrder, 0);
  }

  @Override
  protected final Iterable<Stimulus<E, Iterator<E>>> getStimulusValues() {
    return iteratorStimuli();
  }
}
