

package com.google.common.collect.testing.google;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.SampleElements.Strings;
import java.util.List;

/**
 * Create multisets of strings for tests.
 *
 * @author Jared Levy
 */
@GwtCompatible
public abstract class TestStringMultisetGenerator implements TestMultisetGenerator<String> {
  @Override
  public SampleElements<String> samples() {
    return new Strings();
  }

  @Override
  public Multiset<String> create(Object... elements) {
    String[] array = new String[elements.length];
    int i = 0;
    for (Object e : elements) {
      array[i++] = (String) e;
    }
    return create(array);
  }

  protected abstract Multiset<String> create(String[] elements);

  @Override
  public String[] createArray(int length) {
    return new String[length];
  }

  /** Returns the original element list, unchanged. */
  @Override
  public List<String> order(List<String> insertionOrder) {
    return insertionOrder;
  }
}
