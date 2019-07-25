
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/**
 * A dummy superclass of {@link ImmutableMultimap} that can be instanceof'd without ProGuard
 * retaining additional implementation details of {@link ImmutableMultimap}.
 */
@GwtCompatible
abstract class BaseImmutableMultimap<K, V> extends AbstractMultimap<K, V> {}
