

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.features.CollectionSize;
import java.util.Collection;

/**
 * The subject-generator interface accepted by Collection testers, for testing a Collection at one
 * particular {@link CollectionSize}.
 *
 * <p>This interface should not be implemented outside this package; {@link
 * PerCollectionSizeTestSuiteBuilder} constructs instances of it from a more general {@link
 * TestCollectionGenerator}.
 *
 * @author George van den Driessche
 */
@GwtCompatible
public interface OneSizeTestContainerGenerator<T, E>
    extends TestSubjectGenerator<T>, TestContainerGenerator<T, E> {
  TestContainerGenerator<T, E> getInnerGenerator();

  Collection<E> getSampleElements(int howMany);

  CollectionSize getCollectionSize();
}
