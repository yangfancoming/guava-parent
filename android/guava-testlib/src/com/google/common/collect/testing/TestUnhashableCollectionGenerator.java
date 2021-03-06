package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.SampleElements.Unhashables;
import java.util.Collection;
import java.util.List;

/**
 * Creates collections containing unhashable sample elements, to be tested.
 *
 * @author Regina O'Dell
 */
@GwtCompatible
public abstract class TestUnhashableCollectionGenerator<T extends Collection<UnhashableObject>>
    implements TestCollectionGenerator<UnhashableObject> {
  @Override
  public SampleElements<UnhashableObject> samples() {
    return new Unhashables();
  }

  @Override
  public T create(Object... elements) {
    UnhashableObject[] array = createArray(elements.length);
    int i = 0;
    for (Object e : elements) {
      array[i++] = (UnhashableObject) e;
    }
    return create(array);
  }

  /**
   * Creates a new collection containing the given elements; implement this method instead of {@link
   * #create(Object...)}.
   */
  protected abstract T create(UnhashableObject[] elements);

  @Override
  public UnhashableObject[] createArray(int length) {
    return new UnhashableObject[length];
  }

  @Override
  public Iterable<UnhashableObject> order(List<UnhashableObject> insertionOrder) {
    return insertionOrder;
  }
}
