package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.List;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Implementation of {@link Multimaps#filterKeys(ListMultimap, Predicate)}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
final class FilteredKeyListMultimap<K, V> extends FilteredKeyMultimap<K, V>
    implements ListMultimap<K, V> {
  FilteredKeyListMultimap(ListMultimap<K, V> unfiltered, Predicate<? super K> keyPredicate) {
    super(unfiltered, keyPredicate);
  }

  @Override
  public ListMultimap<K, V> unfiltered() {
    return (ListMultimap<K, V>) super.unfiltered();
  }

  @Override
  public List<V> get(K key) {
    return (List<V>) super.get(key);
  }

  @Override
  public List<V> removeAll(@Nullable Object key) {
    return (List<V>) super.removeAll(key);
  }

  @Override
  public List<V> replaceValues(K key, Iterable<? extends V> values) {
    return (List<V>) super.replaceValues(key, values);
  }
}
