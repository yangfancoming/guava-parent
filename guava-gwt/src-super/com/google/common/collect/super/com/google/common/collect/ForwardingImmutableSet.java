package com.google.common.collect;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * GWT implementation of {@link ImmutableSet} that forwards to another {@code Set} implementation.
 *
 * @author Hayward Chan
 */
@SuppressWarnings("serial") // Serialization only done in GWT.
public abstract class ForwardingImmutableSet<E> extends ImmutableSet<E> {
  private final transient Set<E> delegate;

  ForwardingImmutableSet(Set<E> delegate) {
    // TODO(cpovirk): are we over-wrapping?
    this.delegate = Collections.unmodifiableSet(delegate);
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

  @Override
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

  // TODO(cpovirk): equals(), as well, in case it's any faster than Sets.equalsImpl?

  @Override
  public int hashCode() {
    return delegate.hashCode();
  }
}
