

package com.google.common.collect.testing.features;

import com.google.common.annotations.GwtCompatible;
import java.util.Set;

/**
 * Base class for enumerating the features of an interface to be tested.
 *
 * @param <T> The interface whose features are to be enumerated.
 * @author George van den Driessche
 */
@GwtCompatible
public interface Feature<T> {
  /** Returns the set of features that are implied by this feature. */
  Set<Feature<? super T>> getImpliedFeatures();
}
