package com.google.common.collect;

import java.util.Collection;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A GWT-only class only used by GWT emulations. It is used to consolidate the definitions of method
 * delegation to save code size.
 *
 * @author Hayward Chan
 */
// TODO: Make this class GWT serializable.
class ForwardingImmutableCollection<E> extends ImmutableCollection<E> {

  final transient Collection<E> delegate;

  ForwardingImmutableCollection(Collection<E> delegate) {
    this.delegate = delegate;
  }

  @Override
  public UnmodifiableIterator<E> iterator() {
    return Iterators.unmodifiableIterator(delegate.iterator());
  }

  @Override
  public boolean contains(@Nullable Object object) {
    return object != null && delegate.contains(object);
  }

  @Override
  public boolean containsAll(Collection<?> targets) {
    return delegate.containsAll(targets);
  }

  public int size() {
    return delegate.size();
  }

  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public Object[] toArray() {
    return delegate.toArray();
  }

  @Override
  public <T> T[] toArray(T[] other) {
    return delegate.toArray(other);
  }

  @Override
  public String toString() {
    return delegate.toString();
  }
}
