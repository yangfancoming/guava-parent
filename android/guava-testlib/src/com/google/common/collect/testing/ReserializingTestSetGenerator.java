

package com.google.common.collect.testing;

import com.google.common.annotations.GwtIncompatible;
import java.util.Set;

/**
 * Reserializes the sets created by another test set generator.
 *
 * <p>TODO: make CollectionTestSuiteBuilder test reserialized collections
 *
 * @author Jesse Wilson
 */
@GwtIncompatible
public class ReserializingTestSetGenerator<E> extends ReserializingTestCollectionGenerator<E>
    implements TestSetGenerator<E> {

  ReserializingTestSetGenerator(TestSetGenerator<E> delegate) {
    super(delegate);
  }

  public static <E> TestSetGenerator<E> newInstance(TestSetGenerator<E> delegate) {
    return new ReserializingTestSetGenerator<E>(delegate);
  }

  @Override
  public Set<E> create(Object... elements) {
    return (Set<E>) super.create(elements);
  }
}
