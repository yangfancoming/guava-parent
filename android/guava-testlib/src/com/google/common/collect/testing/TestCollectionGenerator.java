

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;

/**
 * Creates collections, containing sample elements, to be tested.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
public interface TestCollectionGenerator<E> extends TestContainerGenerator<Collection<E>, E> {}
