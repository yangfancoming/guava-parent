package com.google.common.collect;

import java.util.Map;

/**
 * GWt emulation of {@link RegularImmutableMap}.
 *
 * @author Hayward Chan
 */
final class RegularImmutableMap<K, V> extends ForwardingImmutableMap<K, V> {

  RegularImmutableMap(Map<? extends K, ? extends V> delegate) {
    super(delegate);
  }

  RegularImmutableMap(Entry<? extends K, ? extends V>... entries) {
    super(entries);
  }
}
