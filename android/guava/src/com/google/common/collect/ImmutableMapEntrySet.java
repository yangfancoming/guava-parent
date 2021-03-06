

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * {@code entrySet()} implementation for {@link ImmutableMap}.
 *
 * @author Jesse Wilson
 * @author Kevin Bourrillion
 */
@GwtCompatible(emulated = true)
abstract class ImmutableMapEntrySet<K, V> extends ImmutableSet<Entry<K, V>> {
  static final class RegularEntrySet<K, V> extends ImmutableMapEntrySet<K, V> {
    @Weak private final transient ImmutableMap<K, V> map;
    private final transient ImmutableList<Entry<K, V>> entries;

    RegularEntrySet(ImmutableMap<K, V> map, Entry<K, V>[] entries) {
      this(map, ImmutableList.<Entry<K, V>>asImmutableList(entries));
    }

    RegularEntrySet(ImmutableMap<K, V> map, ImmutableList<Entry<K, V>> entries) {
      this.map = map;
      this.entries = entries;
    }

    @Override
    ImmutableMap<K, V> map() {
      return map;
    }

    @Override
    @GwtIncompatible("not used in GWT")
    int copyIntoArray(Object[] dst, int offset) {
      return entries.copyIntoArray(dst, offset);
    }

    @Override
    public UnmodifiableIterator<Entry<K, V>> iterator() {
      return entries.iterator();
    }

    @Override
    ImmutableList<Entry<K, V>> createAsList() {
      return entries;
    }
  }

  ImmutableMapEntrySet() {}

  abstract ImmutableMap<K, V> map();

  @Override
  public int size() {
    return map().size();
  }

  @Override
  public boolean contains(@NullableDecl Object object) {
    if (object instanceof Entry) {
      Entry<?, ?> entry = (Entry<?, ?>) object;
      V value = map().get(entry.getKey());
      return value != null && value.equals(entry.getValue());
    }
    return false;
  }

  @Override
  boolean isPartialView() {
    return map().isPartialView();
  }

  @Override
  @GwtIncompatible // not used in GWT
  boolean isHashCodeFast() {
    return map().isHashCodeFast();
  }

  @Override
  public int hashCode() {
    return map().hashCode();
  }

  @GwtIncompatible // serialization
  @Override
  Object writeReplace() {
    return new EntrySetSerializedForm<>(map());
  }

  @GwtIncompatible // serialization
  private static class EntrySetSerializedForm<K, V> implements Serializable {
    final ImmutableMap<K, V> map;

    EntrySetSerializedForm(ImmutableMap<K, V> map) {
      this.map = map;
    }

    Object readResolve() {
      return map.entrySet();
    }

    private static final long serialVersionUID = 0;
  }
}
