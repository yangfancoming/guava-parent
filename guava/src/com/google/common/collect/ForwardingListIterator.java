

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ListIterator;

/**
 * A list iterator which forwards all its method calls to another list iterator. Subclasses should
 * override one or more methods to modify the behavior of the backing iterator as desired per the <a
 * href="http://en.wikipedia.org/wiki/Decorator_pattern">decorator pattern</a>.
 *
 * <p><b>{@code default} method warning:</b> This class forwards calls to <i>only some</i> {@code
 * default} methods. Specifically, it forwards calls only for methods that existed <a
 * href="https://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html">before {@code
 * default} methods were introduced</a>. For newer methods, like {@code forEachRemaining}, it
 * inherits their default implementations. When those implementations invoke methods, they invoke
 * methods on the {@code ForwardingListIterator}.
 *
 * @author Mike Bostock
 * @since 2.0
 */
@GwtCompatible
public abstract class ForwardingListIterator<E> extends ForwardingIterator<E>
    implements ListIterator<E> {

  /** Constructor for use by subclasses. */
  protected ForwardingListIterator() {}

  @Override
  protected abstract ListIterator<E> delegate();

  @Override
  public void add(E element) {
    delegate().add(element);
  }

  @Override
  public boolean hasPrevious() {
    return delegate().hasPrevious();
  }

  @Override
  public int nextIndex() {
    return delegate().nextIndex();
  }

  @CanIgnoreReturnValue
  @Override
  public E previous() {
    return delegate().previous();
  }

  @Override
  public int previousIndex() {
    return delegate().previousIndex();
  }

  @Override
  public void set(E element) {
    delegate().set(element);
  }
}
