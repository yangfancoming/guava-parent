

package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility {@link RemovalListener} implementations intended for use in testing.
 *
 * @author mike nonemacher
 */
@GwtCompatible(emulated = true)
class TestingRemovalListeners {

  /** Returns a new no-op {@code RemovalListener}. */
  static <K, V> NullRemovalListener<K, V> nullRemovalListener() {
    return new NullRemovalListener<>();
  }

  /** Type-inferring factory method for creating a {@link QueuingRemovalListener}. */
  @GwtIncompatible // ConcurrentLinkedQueue
  static <K, V> QueuingRemovalListener<K, V> queuingRemovalListener() {
    return new QueuingRemovalListener<>();
  }

  /** Type-inferring factory method for creating a {@link CountingRemovalListener}. */
  static <K, V> CountingRemovalListener<K, V> countingRemovalListener() {
    return new CountingRemovalListener<>();
  }

  /** {@link RemovalListener} that adds all {@link RemovalNotification} objects to a queue. */
  @GwtIncompatible // ConcurrentLinkedQueue
  static class QueuingRemovalListener<K, V> extends ConcurrentLinkedQueue<RemovalNotification<K, V>>
      implements RemovalListener<K, V> {

    @Override
    public void onRemoval(RemovalNotification<K, V> notification) {
      add(notification);
    }
  }

  /**
   * {@link RemovalListener} that counts each {@link RemovalNotification} it receives, and provides
   * access to the most-recently received one.
   */
  static class CountingRemovalListener<K, V> implements RemovalListener<K, V> {
    private final AtomicInteger count = new AtomicInteger();
    private volatile RemovalNotification<K, V> lastNotification;

    @Override
    public void onRemoval(RemovalNotification<K, V> notification) {
      count.incrementAndGet();
      lastNotification = notification;
    }

    public int getCount() {
      return count.get();
    }

    public K getLastEvictedKey() {
      return lastNotification.getKey();
    }

    public V getLastEvictedValue() {
      return lastNotification.getValue();
    }

    public RemovalNotification<K, V> getLastNotification() {
      return lastNotification;
    }
  }

  /** No-op {@link RemovalListener}. */
  static class NullRemovalListener<K, V> implements RemovalListener<K, V> {
    @Override
    public void onRemoval(RemovalNotification<K, V> notification) {}
  }
}
