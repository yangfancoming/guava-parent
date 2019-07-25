

package com.google.common.collect;

import java.util.Map;

/**
 * GWT emulation of {@link JdkBackedImmutableMap}. Never used, but must exist so that the client is
 * willing to deserialize maps that were this type on the server.
 */
final class JdkBackedImmutableMap<K, V> extends ForwardingImmutableMap<K, V> {
  JdkBackedImmutableMap(Map<? extends K, ? extends V> delegate) {
    super(delegate);
  }
}
