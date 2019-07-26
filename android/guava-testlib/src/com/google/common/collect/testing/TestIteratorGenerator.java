

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

/**
 * Creates iterators to be tested.
 *
 * @param <E> the element type of the iterator.
 * @author George van den Driessche
 */
@GwtCompatible
public interface TestIteratorGenerator<E> {
  Iterator<E> get();
}
