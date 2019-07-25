

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.SampleElements.Chars;
import java.util.List;

/**
 * Generates {@code List<Character>} instances for test suites.
 *
 * @author Kevin Bourrillion
 * @author Louis Wasserman
 */
@GwtCompatible
public abstract class TestCharacterListGenerator implements TestListGenerator<Character> {
  @Override
  public SampleElements<Character> samples() {
    return new Chars();
  }

  @Override
  public List<Character> create(Object... elements) {
    Character[] array = new Character[elements.length];
    int i = 0;
    for (Object e : elements) {
      array[i++] = (Character) e;
    }
    return create(array);
  }

  /**
   * Creates a new collection containing the given elements; implement this method instead of {@link
   * #create(Object...)}.
   */
  protected abstract List<Character> create(Character[] elements);

  @Override
  public Character[] createArray(int length) {
    return new Character[length];
  }

  /** Returns the original element list, unchanged. */
  @Override
  public List<Character> order(List<Character> insertionOrder) {
    return insertionOrder;
  }
}
