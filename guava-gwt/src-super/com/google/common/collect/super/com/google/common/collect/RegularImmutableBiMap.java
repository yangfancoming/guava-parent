

package com.google.common.collect;

import java.util.HashMap;

/**
 * GWT emulation of {@link RegularImmutableBiMap}.
 *
 * @author Jared Levy
 * @author Hayward Chan
 */
@SuppressWarnings("serial")
class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
  static final RegularImmutableBiMap<Object, Object> EMPTY =
      new RegularImmutableBiMap<Object, Object>();

  // This reference is used both by the GWT compiler to infer the elements
  // of the lists that needs to be serialized.
  private ImmutableBiMap<V, K> inverse;

  RegularImmutableBiMap() {
    super(new RegularImmutableMap<K, V>(new HashMap<K, V>()));
    this.inverse = (ImmutableBiMap<V, K>) this;
  }

  RegularImmutableBiMap(ImmutableMap<K, V> delegate) {
    super(delegate);

    ImmutableMap.Builder<V, K> builder = ImmutableMap.builder();
    for (Entry<K, V> entry : delegate.entrySet()) {
      builder.put(entry.getValue(), entry.getKey());
    }
    ImmutableMap<V, K> backwardMap = builder.build();
    this.inverse = new RegularImmutableBiMap<V, K>(backwardMap, this);
  }

  RegularImmutableBiMap(ImmutableMap<K, V> delegate, ImmutableBiMap<V, K> inverse) {
    super(delegate);
    this.inverse = inverse;
  }

  @Override
  public ImmutableBiMap<V, K> inverse() {
    return inverse;
  }
}
