

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.SampleElements.Ints;
import java.util.List;
import java.util.Set;

/**
 * Create integer sets for collection tests.
 *
 * @author Gregory Kick
 */
@GwtCompatible
public abstract class TestIntegerSetGenerator implements TestSetGenerator<Integer> {
  @Override
  public SampleElements<Integer> samples() {
    return new Ints();
  }

  @Override
  public Set<Integer> create(Object... elements) {
    Integer[] array = new Integer[elements.length];
    int i = 0;
    for (Object e : elements) {
      array[i++] = (Integer) e;
    }
    return create(array);
  }

  protected abstract Set<Integer> create(Integer[] elements);

  @Override
  public Integer[] createArray(int length) {
    return new Integer[length];
  }

  /**
   * {@inheritDoc}
   *
   * <p>By default, returns the supplied elements in their given order; however, generators for
   * containers with a known order other than insertion order must override this method.
   *
   * <p>Note: This default implementation is overkill (but valid) for an unordered container. An
   * equally valid implementation for an unordered container is to throw an exception. The chosen
   * implementation, however, has the advantage of working for insertion-ordered containers, as
   * well.
   */
  @Override
  public List<Integer> order(List<Integer> insertionOrder) {
    return insertionOrder;
  }
}
