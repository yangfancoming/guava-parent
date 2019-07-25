

package com.google.common.cache;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import junit.framework.TestCase;

/**
 * Unit test for {@link AbstractLoadingCache}.
 *
 * @author Charles Fry
 */
public class AbstractLoadingCacheTest extends TestCase {

  public void testGetUnchecked_checked() {
    final Exception cause = new Exception();
    final AtomicReference<Object> valueRef = new AtomicReference<>();
    LoadingCache<Object, Object> cache =
        new AbstractLoadingCache<Object, Object>() {
          @Override
          public Object get(Object key) throws ExecutionException {
            Object v = valueRef.get();
            if (v == null) {
              throw new ExecutionException(cause);
            }
            return v;
          }

          @Override
          public Object getIfPresent(Object key) {
            return valueRef.get();
          }
        };

    try {
      cache.getUnchecked(new Object());
      fail();
    } catch (UncheckedExecutionException expected) {
      assertThat(expected).hasCauseThat().isEqualTo(cause);
    }

    Object newValue = new Object();
    valueRef.set(newValue);
    assertSame(newValue, cache.getUnchecked(new Object()));
  }

  public void testGetUnchecked_unchecked() {
    final RuntimeException cause = new RuntimeException();
    final AtomicReference<Object> valueRef = new AtomicReference<>();
    LoadingCache<Object, Object> cache =
        new AbstractLoadingCache<Object, Object>() {
          @Override
          public Object get(Object key) throws ExecutionException {
            Object v = valueRef.get();
            if (v == null) {
              throw new ExecutionException(cause);
            }
            return v;
          }

          @Override
          public Object getIfPresent(Object key) {
            return valueRef.get();
          }
        };

    try {
      cache.getUnchecked(new Object());
      fail();
    } catch (UncheckedExecutionException expected) {
      assertThat(expected).hasCauseThat().isEqualTo(cause);
    }

    Object newValue = new Object();
    valueRef.set(newValue);
    assertSame(newValue, cache.getUnchecked(new Object()));
  }

  public void testGetUnchecked_error() {
    final Error cause = new Error();
    final AtomicReference<Object> valueRef = new AtomicReference<>();
    LoadingCache<Object, Object> cache =
        new AbstractLoadingCache<Object, Object>() {
          @Override
          public Object get(Object key) throws ExecutionException {
            Object v = valueRef.get();
            if (v == null) {
              throw new ExecutionError(cause);
            }
            return v;
          }

          @Override
          public Object getIfPresent(Object key) {
            return valueRef.get();
          }
        };

    try {
      cache.getUnchecked(new Object());
      fail();
    } catch (ExecutionError expected) {
      assertThat(expected).hasCauseThat().isEqualTo(cause);
    }

    Object newValue = new Object();
    valueRef.set(newValue);
    assertSame(newValue, cache.getUnchecked(new Object()));
  }

  public void testGetUnchecked_otherThrowable() {
    final Throwable cause = new Throwable();
    final AtomicReference<Object> valueRef = new AtomicReference<>();
    LoadingCache<Object, Object> cache =
        new AbstractLoadingCache<Object, Object>() {
          @Override
          public Object get(Object key) throws ExecutionException {
            Object v = valueRef.get();
            if (v == null) {
              throw new ExecutionException(cause);
            }
            return v;
          }

          @Override
          public Object getIfPresent(Object key) {
            return valueRef.get();
          }
        };

    try {
      cache.getUnchecked(new Object());
      fail();
    } catch (UncheckedExecutionException expected) {
      assertThat(expected).hasCauseThat().isEqualTo(cause);
    }

    Object newValue = new Object();
    valueRef.set(newValue);
    assertSame(newValue, cache.getUnchecked(new Object()));
  }
}
