package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/**
 * A supertype for filtered {@link SetMultimap} implementations.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
interface FilteredSetMultimap<K, V> extends FilteredMultimap<K, V>, SetMultimap<K, V> {
  @Override
  SetMultimap<K, V> unfiltered();
}
