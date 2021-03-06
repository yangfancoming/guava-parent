

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map.Entry;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * {@code values()} implementation for {@link ImmutableMap}.
 *
 * @author Jesse Wilson
 * @author Kevin Bourrillion
 */
@GwtCompatible(emulated = true)
final class ImmutableMapValues<K, V> extends ImmutableCollection<V> {
  @Weak private final ImmutableMap<K, V> map;

  ImmutableMapValues(ImmutableMap<K, V> map) {
    this.map = map;
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public UnmodifiableIterator<V> iterator() {
    return new UnmodifiableIterator<V>() {
      final UnmodifiableIterator<Entry<K, V>> entryItr = map.entrySet().iterator();

      @Override
      public boolean hasNext() {
        return entryItr.hasNext();
      }

      @Override
      public V next() {
        return entryItr.next().getValue();
      }
    };
  }

  @Override
  public boolean contains(@NullableDecl Object object) {
    return object != null && Iterators.contains(iterator(), object);
  }

  @Override
  boolean isPartialView() {
    return true;
  }

  @Override
  public ImmutableList<V> asList() {
    final ImmutableList<Entry<K, V>> entryList = map.entrySet().asList();
    return new ImmutableList<V>() {
      @Override
      public V get(int index) {
        return entryList.get(index).getValue();
      }

      @Override
      boolean isPartialView() {
        return true;
      }

      @Override
      public int size() {
        return entryList.size();
      }
    };
  }

  @GwtIncompatible // serialization
  @Override
  Object writeReplace() {
    return new SerializedForm<V>(map);
  }

  @GwtIncompatible // serialization
  private static class SerializedForm<V> implements Serializable {
    final ImmutableMap<?, V> map;

    SerializedForm(ImmutableMap<?, V> map) {
      this.map = map;
    }

    Object readResolve() {
      return map.values();
    }

    private static final long serialVersionUID = 0;
  }
}
