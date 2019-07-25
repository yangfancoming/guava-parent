package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedSet;

/**
 * GWT emulation of {@link RegularImmutableSortedSet}.
 *
 * @author Hayward Chan
 */
final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {

  /** true if this set is a subset of another immutable sorted set. */
  final boolean isSubset;

  private Comparator<E> unusedComparatorForSerialization;
  private E unusedElementForSerialization;

  RegularImmutableSortedSet(SortedSet<E> delegate, boolean isSubset) {
    super(delegate);
    this.isSubset = isSubset;
  }

  @Override
  ImmutableList<E> createAsList() {
    return new ImmutableSortedAsList<E>(this, ImmutableList.<E>asImmutableList(toArray()));
  }
}
