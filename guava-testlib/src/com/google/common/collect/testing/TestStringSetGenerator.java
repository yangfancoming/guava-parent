

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.SampleElements.Strings;
import java.util.List;
import java.util.Set;

/**
 * Create string sets for collection tests.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
public abstract class TestStringSetGenerator implements TestSetGenerator<String> {
  @Override
  public SampleElements<String> samples() {
    return new Strings();
  }

  @Override
  public Set<String> create(Object... elements) {
    String[] array = new String[elements.length];
    int i = 0;
    for (Object e : elements) {
      array[i++] = (String) e;
    }
    return create(array);
  }

  protected abstract Set<String> create(String[] elements);

  @Override
  public String[] createArray(int length) {
    return new String[length];
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
  public List<String> order(List<String> insertionOrder) {
    return insertionOrder;
  }
}
