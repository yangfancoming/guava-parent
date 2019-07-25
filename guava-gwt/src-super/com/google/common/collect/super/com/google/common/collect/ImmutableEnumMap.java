package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.Map.Entry;

/**
 * GWT emulation of {@link ImmutableEnumMap}. The type parameter is not bounded by {@code Enum<E>}
 * to avoid code-size bloat.
 *
 * @author Hayward Chan
 */
final class ImmutableEnumMap<K, V> extends ForwardingImmutableMap<K, V> {
  static <K, V> ImmutableMap<K, V> asImmutable(Map<K, V> map) {
    for (Entry<K, V> entry : checkNotNull(map).entrySet()) {
      checkNotNull(entry.getKey());
      checkNotNull(entry.getValue());
    }
    return new ImmutableEnumMap<K, V>(map);
  }

  private ImmutableEnumMap(Map<? extends K, ? extends V> delegate) {
    super(delegate);
  }
}
