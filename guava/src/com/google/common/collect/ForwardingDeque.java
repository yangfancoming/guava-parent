package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Deque;
import java.util.Iterator;

/**
 * A deque which forwards all its method calls to another deque. Subclasses should override one or
 * more methods to modify the behavior of the backing deque as desired per the <a
 * href="http://en.wikipedia.org/wiki/Decorator_pattern">decorator pattern</a>.
 *
 * <p><b>Warning:</b> The methods of {@code ForwardingDeque} forward <b>indiscriminately</b> to the
 * methods of the delegate. For example, overriding {@link #add} alone <b>will not</b> change the
 * behavior of {@link #offer} which can lead to unexpected behavior. In this case, you should
 * override {@code offer} as well.
 *
 * <p><b>{@code default} method warning:</b> This class does <i>not</i> forward calls to {@code
 * default} methods. Instead, it inherits their default implementations. When those implementations
 * invoke methods, they invoke methods on the {@code ForwardingDeque}.
 *
 * @author Kurt Alfred Kluever
 * @since 12.0
 */
@GwtIncompatible
public abstract class ForwardingDeque<E> extends ForwardingQueue<E> implements Deque<E> {

  /** Constructor for use by subclasses. */
  protected ForwardingDeque() {}

  @Override
  protected abstract Deque<E> delegate();

  @Override
  public void addFirst(E e) {
    delegate().addFirst(e);
  }

  @Override
  public void addLast(E e) {
    delegate().addLast(e);
  }

  @Override
  public Iterator<E> descendingIterator() {
    return delegate().descendingIterator();
  }

  @Override
  public E getFirst() {
    return delegate().getFirst();
  }

  @Override
  public E getLast() {
    return delegate().getLast();
  }

  @CanIgnoreReturnValue // TODO(cpovirk): Consider removing this?
  @Override
  public boolean offerFirst(E e) {
    return delegate().offerFirst(e);
  }

  @CanIgnoreReturnValue // TODO(cpovirk): Consider removing this?
  @Override
  public boolean offerLast(E e) {
    return delegate().offerLast(e);
  }

  @Override
  public E peekFirst() {
    return delegate().peekFirst();
  }

  @Override
  public E peekLast() {
    return delegate().peekLast();
  }

  @CanIgnoreReturnValue // TODO(cpovirk): Consider removing this?
  @Override
  public E pollFirst() {
    return delegate().pollFirst();
  }

  @CanIgnoreReturnValue // TODO(cpovirk): Consider removing this?
  @Override
  public E pollLast() {
    return delegate().pollLast();
  }

  @CanIgnoreReturnValue
  @Override
  public E pop() {
    return delegate().pop();
  }

  @Override
  public void push(E e) {
    delegate().push(e);
  }

  @CanIgnoreReturnValue
  @Override
  public E removeFirst() {
    return delegate().removeFirst();
  }

  @CanIgnoreReturnValue
  @Override
  public E removeLast() {
    return delegate().removeLast();
  }

  @CanIgnoreReturnValue
  @Override
  public boolean removeFirstOccurrence(Object o) {
    return delegate().removeFirstOccurrence(o);
  }

  @CanIgnoreReturnValue
  @Override
  public boolean removeLastOccurrence(Object o) {
    return delegate().removeLastOccurrence(o);
  }
}
