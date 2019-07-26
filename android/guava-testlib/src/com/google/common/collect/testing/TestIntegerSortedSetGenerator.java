

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

/**
 * Create integer sets for testing collections that are sorted by natural ordering.
 *
 * @author Chris Povirk
 * @author Jared Levy
 */
@GwtCompatible
public abstract class TestIntegerSortedSetGenerator extends TestIntegerSetGenerator {
  @Override
  protected abstract SortedSet<Integer> create(Integer[] elements);

  /** Sorts the elements by their natural ordering. */
  @Override
  public List<Integer> order(List<Integer> insertionOrder) {
    Collections.sort(insertionOrder);
    return insertionOrder;
  }
}
