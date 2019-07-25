

package com.google.common.cache;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.Executor;

/**
 * A collection of common removal listeners.
 *
 * @author Charles Fry
 * @since 10.0
 */
@GwtIncompatible
public final class RemovalListeners {

  private RemovalListeners() {}

  /**
   * Returns a {@code RemovalListener} which processes all eviction notifications using {@code
   * executor}.
   *
   * @param listener the backing listener
   * @param executor the executor with which removal notifications are asynchronously executed
   */
  public static <K, V> RemovalListener<K, V> asynchronous(
      final RemovalListener<K, V> listener, final Executor executor) {
    checkNotNull(listener);
    checkNotNull(executor);
    return new RemovalListener<K, V>() {
      @Override
      public void onRemoval(final RemovalNotification<K, V> notification) {
        executor.execute(
            new Runnable() {
              @Override
              public void run() {
                listener.onRemoval(notification);
              }
            });
      }
    };
  }
}
