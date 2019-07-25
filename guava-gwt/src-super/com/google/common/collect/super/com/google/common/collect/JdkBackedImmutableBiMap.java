
package com.google.common.collect;

/**
 * GWT emulation of {@link JdkBackedImmutableBiMap}. Never used, but must exist so that the client
 * is willing to deserialize maps that were this type on the server.
 */
class JdkBackedImmutableBiMap<K, V> extends RegularImmutableBiMap<K, V> {
  private JdkBackedImmutableBiMap(ImmutableMap<K, V> delegate, ImmutableBiMap<V, K> inverse) {
    super(delegate, inverse);
  }
}
