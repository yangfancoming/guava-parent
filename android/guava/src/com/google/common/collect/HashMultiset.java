

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;

/**
 * Multiset implementation that uses hashing for key and entry access.
 *
 * @author Kevin Bourrillion
 * @author Jared Levy
 * @since 2.0
 */
@GwtCompatible(serializable = true, emulated = true)
public class HashMultiset<E> extends AbstractMapBasedMultiset<E> {

  /** Creates a new, empty {@code HashMultiset} using the default initial capacity. */
  public static <E> HashMultiset<E> create() {
    return create(ObjectCountHashMap.DEFAULT_SIZE);
  }

  /**
   * Creates a new, empty {@code HashMultiset} with the specified expected number of distinct
   * elements.
   *
   * @param distinctElements the expected number of distinct elements
   * @throws IllegalArgumentException if {@code distinctElements} is negative
   */
  public static <E> HashMultiset<E> create(int distinctElements) {
    return new HashMultiset<E>(distinctElements);
  }

  /**
   * Creates a new {@code HashMultiset} containing the specified elements.
   *
   * <p>This implementation is highly efficient when {@code elements} is itself a {@link Multiset}.
   *
   * @param elements the elements that the multiset should contain
   */
  public static <E> HashMultiset<E> create(Iterable<? extends E> elements) {
    HashMultiset<E> multiset = create(Multisets.inferDistinctElements(elements));
    Iterables.addAll(multiset, elements);
    return multiset;
  }

  HashMultiset(int distinctElements) {
    super(distinctElements);
  }

  @Override
  void init(int distinctElements) {
    backingMap = new ObjectCountHashMap<>(distinctElements);
  }

  @GwtIncompatible // Not needed in emulated source.
  private static final long serialVersionUID = 0;
}
