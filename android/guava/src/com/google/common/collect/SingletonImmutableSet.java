

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;

/**
 * Implementation of {@link ImmutableSet} with exactly one element.
 *
 * @author Kevin Bourrillion
 * @author Nick Kralevich
 */
@GwtCompatible(serializable = true, emulated = true)
@SuppressWarnings("serial") // uses writeReplace(), not default serialization
final class SingletonImmutableSet<E> extends ImmutableSet<E> {

  final transient E element;
  // This is transient because it will be recalculated on the first
  // call to hashCode().
  //
  // A race condition is avoided since threads will either see that the value
  // is zero and recalculate it themselves, or two threads will see it at
  // the same time, and both recalculate it.  If the cachedHashCode is 0,
  // it will always be recalculated, unfortunately.
  @LazyInit private transient int cachedHashCode;

  SingletonImmutableSet(E element) {
    this.element = Preconditions.checkNotNull(element);
  }

  SingletonImmutableSet(E element, int hashCode) {
    // Guaranteed to be non-null by the presence of the pre-computed hash code.
    this.element = element;
    cachedHashCode = hashCode;
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public boolean contains(Object target) {
    return element.equals(target);
  }

  @Override
  public UnmodifiableIterator<E> iterator() {
    return Iterators.singletonIterator(element);
  }

  @Override
  ImmutableList<E> createAsList() {
    return ImmutableList.of(element);
  }

  @Override
  boolean isPartialView() {
    return false;
  }

  @Override
  int copyIntoArray(Object[] dst, int offset) {
    dst[offset] = element;
    return offset + 1;
  }

  @Override
  public final int hashCode() {
    // Racy single-check.
    int code = cachedHashCode;
    if (code == 0) {
      cachedHashCode = code = element.hashCode();
    }
    return code;
  }

  @Override
  boolean isHashCodeFast() {
    return cachedHashCode != 0;
  }

  @Override
  public String toString() {
    return '[' + element.toString() + ']';
  }
}
