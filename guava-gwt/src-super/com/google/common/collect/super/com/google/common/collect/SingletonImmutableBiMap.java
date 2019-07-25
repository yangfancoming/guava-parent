package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collections;

/**
 * GWT emulation of {@link SingletonImmutableBiMap}.
 *
 * @author Hayward Chan
 */
final class SingletonImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {

  // These references are used both by the custom field serializer, and by the
  // GWT compiler to infer the keys and values of the map that needs to be
  // serialized.
  //
  // Although they are non-final, they are package private and the reference is
  // never modified after a map is constructed.
  K singleKey;
  V singleValue;

  transient SingletonImmutableBiMap<V, K> inverse;

  SingletonImmutableBiMap(K key, V value) {
    super(Collections.singletonMap(checkNotNull(key), checkNotNull(value)));
    this.singleKey = key;
    this.singleValue = value;
  }

  private SingletonImmutableBiMap(K key, V value, SingletonImmutableBiMap<V, K> inverse) {
    super(Collections.singletonMap(checkNotNull(key), checkNotNull(value)));
    this.singleKey = key;
    this.singleValue = value;
    this.inverse = inverse;
  }

  @Override
  public ImmutableBiMap<V, K> inverse() {
    ImmutableBiMap<V, K> result = inverse;
    if (result == null) {
      return inverse = new SingletonImmutableBiMap<V, K>(singleValue, singleKey, this);
    } else {
      return result;
    }
  }

  @Override
  public ImmutableSet<V> values() {
    return ImmutableSet.of(singleValue);
  }
}
