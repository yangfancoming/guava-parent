package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Maps.IteratorBasedAbstractMap;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Skeletal implementation of {@link NavigableMap}.
 *
 * @author Louis Wasserman
 */
@GwtIncompatible
abstract class AbstractNavigableMap<K, V> extends IteratorBasedAbstractMap<K, V>
    implements NavigableMap<K, V> {

  @Override
  public abstract @Nullable V get(@Nullable Object key);

  @Override
  public @Nullable Entry<K, V> firstEntry() {
    return Iterators.getNext(entryIterator(), null);
  }

  @Override
  public @Nullable Entry<K, V> lastEntry() {
    return Iterators.getNext(descendingEntryIterator(), null);
  }

  @Override
  public @Nullable Entry<K, V> pollFirstEntry() {
    return Iterators.pollNext(entryIterator());
  }

  @Override
  public @Nullable Entry<K, V> pollLastEntry() {
    return Iterators.pollNext(descendingEntryIterator());
  }

  @Override
  public K firstKey() {
    Entry<K, V> entry = firstEntry();
    if (entry == null) {
      throw new NoSuchElementException();
    } else {
      return entry.getKey();
    }
  }

  @Override
  public K lastKey() {
    Entry<K, V> entry = lastEntry();
    if (entry == null) {
      throw new NoSuchElementException();
    } else {
      return entry.getKey();
    }
  }

  @Override
  public @Nullable Entry<K, V> lowerEntry(K key) {
    return headMap(key, false).lastEntry();
  }

  @Override
  public @Nullable Entry<K, V> floorEntry(K key) {
    return headMap(key, true).lastEntry();
  }

  @Override
  public @Nullable Entry<K, V> ceilingEntry(K key) {
    return tailMap(key, true).firstEntry();
  }

  @Override
  public @Nullable Entry<K, V> higherEntry(K key) {
    return tailMap(key, false).firstEntry();
  }

  @Override
  public K lowerKey(K key) {
    return Maps.keyOrNull(lowerEntry(key));
  }

  @Override
  public K floorKey(K key) {
    return Maps.keyOrNull(floorEntry(key));
  }

  @Override
  public K ceilingKey(K key) {
    return Maps.keyOrNull(ceilingEntry(key));
  }

  @Override
  public K higherKey(K key) {
    return Maps.keyOrNull(higherEntry(key));
  }

  abstract Iterator<Entry<K, V>> descendingEntryIterator();

  @Override
  public SortedMap<K, V> subMap(K fromKey, K toKey) {
    return subMap(fromKey, true, toKey, false);
  }

  @Override
  public SortedMap<K, V> headMap(K toKey) {
    return headMap(toKey, false);
  }

  @Override
  public SortedMap<K, V> tailMap(K fromKey) {
    return tailMap(fromKey, true);
  }

  @Override
  public NavigableSet<K> navigableKeySet() {
    return new Maps.NavigableKeySet<>(this);
  }

  @Override
  public Set<K> keySet() {
    return navigableKeySet();
  }

  @Override
  public NavigableSet<K> descendingKeySet() {
    return descendingMap().navigableKeySet();
  }

  @Override
  public NavigableMap<K, V> descendingMap() {
    return new DescendingMap();
  }

  private final class DescendingMap extends Maps.DescendingMap<K, V> {
    @Override
    NavigableMap<K, V> forward() {
      return AbstractNavigableMap.this;
    }

    @Override
    Iterator<Entry<K, V>> entryIterator() {
      return descendingEntryIterator();
    }
  }
}