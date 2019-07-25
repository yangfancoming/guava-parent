

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * This class provides a skeletal implementation of the {@code Iterator} interface for sequences
 * whose next element can always be derived from the previous element. Null elements are not
 * supported, nor is the {@link #remove()} method.
 *
 * <p>Example:
 *
 * <pre>{@code
 * Iterator<Integer> powersOfTwo =
 *     new AbstractSequentialIterator<Integer>(1) {
 *       protected Integer computeNext(Integer previous) {
 *         return (previous == 1 << 30) ? null : previous * 2;
 *       }
 *     };
 * }</pre>
 *
 * @author Chris Povirk
 * @since 12.0 (in Guava as {@code AbstractLinkedIterator} since 8.0)
 */
@GwtCompatible
public abstract class AbstractSequentialIterator<T> extends UnmodifiableIterator<T> {
  private @Nullable T nextOrNull;

  /**
   * Creates a new iterator with the given first element, or, if {@code firstOrNull} is null,
   * creates a new empty iterator.
   */
  protected AbstractSequentialIterator(@Nullable T firstOrNull) {
    this.nextOrNull = firstOrNull;
  }

  /**
   * Returns the element that follows {@code previous}, or returns {@code null} if no elements
   * remain. This method is invoked during each call to {@link #next()} in order to compute the
   * result of a <i>future</i> call to {@code next()}.
   */
  @Nullable
  protected abstract T computeNext(T previous);

  @Override
  public final boolean hasNext() {
    return nextOrNull != null;
  }

  @Override
  public final T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    try {
      return nextOrNull;
    } finally {
      nextOrNull = computeNext(nextOrNull);
    }
  }
}
