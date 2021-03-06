package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multisets.UnmodifiableMultiset;
import java.util.Comparator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

/**
 * Implementation of {@link Multisets#unmodifiableSortedMultiset(SortedMultiset)}, split out into
 * its own file so it can be GWT emulated (to deal with the differing elementSet() types in GWT and
 * non-GWT).
 *
 * @author Louis Wasserman
 */
@GwtCompatible(emulated = true)
final class UnmodifiableSortedMultiset<E> extends UnmodifiableMultiset<E>
    implements SortedMultiset<E> {
  UnmodifiableSortedMultiset(SortedMultiset<E> delegate) {
    super(delegate);
  }

  @Override
  protected SortedMultiset<E> delegate() {
    return (SortedMultiset<E>) super.delegate();
  }

  @Override
  public Comparator<? super E> comparator() {
    return delegate().comparator();
  }

  @Override
  NavigableSet<E> createElementSet() {
    return Sets.unmodifiableNavigableSet(delegate().elementSet());
  }

  @Override
  public NavigableSet<E> elementSet() {
    return (NavigableSet<E>) super.elementSet();
  }

  @MonotonicNonNullDecl private transient UnmodifiableSortedMultiset<E> descendingMultiset;

  @Override
  public SortedMultiset<E> descendingMultiset() {
    UnmodifiableSortedMultiset<E> result = descendingMultiset;
    if (result == null) {
      result = new UnmodifiableSortedMultiset<E>(delegate().descendingMultiset());
      result.descendingMultiset = this;
      return descendingMultiset = result;
    }
    return result;
  }

  @Override
  public Entry<E> firstEntry() {
    return delegate().firstEntry();
  }

  @Override
  public Entry<E> lastEntry() {
    return delegate().lastEntry();
  }

  @Override
  public Entry<E> pollFirstEntry() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Entry<E> pollLastEntry() {
    throw new UnsupportedOperationException();
  }

  @Override
  public SortedMultiset<E> headMultiset(E upperBound, BoundType boundType) {
    return Multisets.unmodifiableSortedMultiset(delegate().headMultiset(upperBound, boundType));
  }

  @Override
  public SortedMultiset<E> subMultiset(
      E lowerBound, BoundType lowerBoundType, E upperBound, BoundType upperBoundType) {
    return Multisets.unmodifiableSortedMultiset(
        delegate().subMultiset(lowerBound, lowerBoundType, upperBound, upperBoundType));
  }

  @Override
  public SortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType) {
    return Multisets.unmodifiableSortedMultiset(delegate().tailMultiset(lowerBound, boundType));
  }

  private static final long serialVersionUID = 0;
}
