

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/** @see com.google.common.collect.Maps#immutableEntry(Object, Object) */
@GwtCompatible(serializable = true)
class ImmutableEntry<K, V> extends AbstractMapEntry<K, V> implements Serializable {
  @NullableDecl final K key;
  @NullableDecl final V value;

  ImmutableEntry(@NullableDecl K key, @NullableDecl V value) {
    this.key = key;
    this.value = value;
  }

  @Override
  @NullableDecl
  public final K getKey() {
    return key;
  }

  @Override
  @NullableDecl
  public final V getValue() {
    return value;
  }

  @Override
  public final V setValue(V value) {
    throw new UnsupportedOperationException();
  }

  private static final long serialVersionUID = 0;
}
