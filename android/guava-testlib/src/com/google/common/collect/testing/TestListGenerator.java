

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.List;

/**
 * Creates sets, containing sample elements, to be tested.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
public interface TestListGenerator<E> extends TestCollectionGenerator<E> {
  @Override
  List<E> create(Object... elements);
}
