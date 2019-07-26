

package com.google.common.collect.testing.google;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.testing.TestCollectionGenerator;

/**
 * Creates multisets, containing sample elements, to be tested.
 *
 * @author Jared Levy
 */
@GwtCompatible
public interface TestMultisetGenerator<E> extends TestCollectionGenerator<E> {
  @Override
  Multiset<E> create(Object... elements);
}
