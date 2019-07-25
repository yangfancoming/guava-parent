package java.util.concurrent;

import java.util.Map;

/**
 * Minimal GWT emulation of a map providing atomic operations.
 *
 * @author Jesse Wilson
 */
public interface ConcurrentMap<K, V> extends Map<K, V> {

  V putIfAbsent(K key, V value);

  boolean remove(Object key, Object value);

  V replace(K key, V value);

  boolean replace(K key, V oldValue, V newValue);
}
