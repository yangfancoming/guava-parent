

package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * This class provides a skeletal implementation of the {@code Cache} interface to minimize the
 * effort required to implement this interface.
 *
 * <p>To implement a cache, the programmer needs only to extend this class and provide an
 * implementation for the {@link #get(Object)} and {@link #getIfPresent} methods. {@link
 * #getUnchecked}, {@link #get(Object, Callable)}, and {@link #getAll} are implemented in terms of
 * {@code get}; {@link #getAllPresent} is implemented in terms of {@code getIfPresent}; {@link
 * #putAll} is implemented in terms of {@link #put}, {@link #invalidateAll(Iterable)} is implemented
 * in terms of {@link #invalidate}. The method {@link #cleanUp} is a no-op. All other methods throw
 * an {@link UnsupportedOperationException}.
 *
 * @author Charles Fry
 * @since 11.0
 */
@GwtIncompatible
public abstract class AbstractLoadingCache<K, V> extends AbstractCache<K, V>
    implements LoadingCache<K, V> {

  /** Constructor for use by subclasses. */
  protected AbstractLoadingCache() {}

  @Override
  public V getUnchecked(K key) {
    try {
      return get(key);
    } catch (ExecutionException e) {
      throw new UncheckedExecutionException(e.getCause());
    }
  }

  @Override
  public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
    Map<K, V> result = Maps.newLinkedHashMap();
    for (K key : keys) {
      if (!result.containsKey(key)) {
        result.put(key, get(key));
      }
    }
    return ImmutableMap.copyOf(result);
  }

  @Override
  public final V apply(K key) {
    return getUnchecked(key);
  }

  @Override
  public void refresh(K key) {
    throw new UnsupportedOperationException();
  }
}
