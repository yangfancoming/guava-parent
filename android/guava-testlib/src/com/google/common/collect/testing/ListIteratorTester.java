

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * A utility similar to {@link IteratorTester} for testing a {@link ListIterator} against a known
 * good reference implementation. As with {@code IteratorTester}, a concrete subclass must provide
 * target iterators on demand. It also requires three additional constructor parameters: {@code
 * elementsToInsert}, the elements to be passed to {@code set()} and {@code add()} calls; {@code
 * features}, the features supported by the iterator; and {@code expectedElements}, the elements the
 * iterator should return in order.
 *
 * <p>The items in {@code elementsToInsert} will be repeated if {@code steps} is larger than the
 * number of provided elements.
 *
 * @author Chris Povirk
 */
@GwtCompatible
public abstract class ListIteratorTester<E> extends AbstractIteratorTester<E, ListIterator<E>> {
  protected ListIteratorTester(
      int steps,
      Iterable<E> elementsToInsert,
      Iterable<? extends IteratorFeature> features,
      Iterable<E> expectedElements,
      int startIndex) {
    super(steps, elementsToInsert, features, expectedElements, KnownOrder.KNOWN_ORDER, startIndex);
  }

  @Override
  protected final Iterable<? extends Stimulus<E, ? super ListIterator<E>>> getStimulusValues() {
    List<Stimulus<E, ? super ListIterator<E>>> list = new ArrayList<>();
    Helpers.addAll(list, iteratorStimuli());
    Helpers.addAll(list, listIteratorStimuli());
    return list;
  }

  @Override
  protected abstract ListIterator<E> newTargetIterator();
}
