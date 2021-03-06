

package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;

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
  public Spliterator<V> spliterator() {
    return CollectSpliterators.map(map.entrySet().spliterator(), Entry::getValue);
  }

  @Override
  public boolean contains(@Nullable Object object) {
    return object != null && Iterators.contains(iterator(), object);
  }

  @Override
  boolean isPartialView() {
    return true;
  }

  @Override
  public ImmutableList<V> asList() {
    final ImmutableList<Entry<K, V>> entryList = map.entrySet().asList();
    return new ImmutableAsList<V>() {
      @Override
      public V get(int index) {
        return entryList.get(index).getValue();
      }

      @Override
      ImmutableCollection<V> delegateCollection() {
        return ImmutableMapValues.this;
      }
    };
  }

  @GwtIncompatible // serialization
  @Override
  public void forEach(Consumer<? super V> action) {
    checkNotNull(action);
    map.forEach((k, v) -> action.accept(v));
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
