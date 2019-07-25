package com.google.common.collect;

import com.google.common.collect.Multisets.UnmodifiableMultiset;
import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;

/**
 * Implementation of {@link Multisets#unmodifiableSortedMultiset(SortedMultiset)} for GWT.
 *
 * @author Louis Wasserman
 */
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
  SortedSet<E> createElementSet() {
    return Collections.unmodifiableSortedSet(delegate().elementSet());
  }

  @Override
  public SortedSet<E> elementSet() {
    return (SortedSet<E>) super.elementSet();
  }

  private transient UnmodifiableSortedMultiset<E> descendingMultiset;

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
