

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.Set;

/**
 * Creates sets, containing sample elements, to be tested.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
public interface TestSetGenerator<E> extends TestCollectionGenerator<E> {
  @Override
  Set<E> create(Object... elements);
}
