

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.Queue;

/**
 * Creates queues, containing sample elements, to be tested.
 *
 * @author Jared Levy
 */
@GwtCompatible
public interface TestQueueGenerator<E> extends TestCollectionGenerator<E> {
  @Override
  Queue<E> create(Object... elements);
}
