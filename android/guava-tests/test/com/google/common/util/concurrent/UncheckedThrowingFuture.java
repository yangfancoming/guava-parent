

package com.google.common.util.concurrent;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A {@link Future} implementation which always throws directly from calls to {@code get()} (i.e.
 * not wrapped in {@code ExecutionException}. For just a normal failure, use {@link
 * SettableFuture}).
 *
 * <p>Useful for testing the behavior of Future utilities against odd futures.
 *
 * @author Anthony Zana
 */
@GwtCompatible
final class UncheckedThrowingFuture<V> extends AbstractFuture<V> {

  public static <V> ListenableFuture<V> throwingError(Error error) {
    UncheckedThrowingFuture<V> future = new UncheckedThrowingFuture<V>();
    future.complete(checkNotNull(error));
    return future;
  }

  public static <V> ListenableFuture<V> throwingRuntimeException(RuntimeException e) {
    UncheckedThrowingFuture<V> future = new UncheckedThrowingFuture<V>();
    future.complete(checkNotNull(e));
    return future;
  }

  public static <V> UncheckedThrowingFuture<V> incomplete() {
    return new UncheckedThrowingFuture<V>();
  }

  public void complete(RuntimeException e) {
    if (!super.setException(new WrapperException(checkNotNull(e)))) {
      throw new IllegalStateException("Future was already complete: " + this);
    }
  }

  public void complete(Error e) {
    if (!super.setException(new WrapperException(checkNotNull(e)))) {
      throw new IllegalStateException("Future was already complete: " + this);
    }
  }

  private static final class WrapperException extends Exception {
    WrapperException(Throwable t) {
      super(t);
    }
  }

  private static void rethrow(ExecutionException e) throws ExecutionException {
    Throwable wrapper = e.getCause();
    if (wrapper instanceof WrapperException) {
      Throwable cause = wrapper.getCause();
      if (cause instanceof RuntimeException) {
        throw (RuntimeException) cause;
      } else if (cause instanceof Error) {
        throw (Error) cause;
      }
    }
    throw e;
  }

  @Override
  public V get() throws ExecutionException, InterruptedException {
    try {
      super.get();
    } catch (ExecutionException e) {
      rethrow(e);
    }
    throw new AssertionError("Unreachable");
  }

  @Override
  public V get(long timeout, TimeUnit unit)
      throws InterruptedException, ExecutionException, TimeoutException {
    try {
      super.get(timeout, unit);
    } catch (ExecutionException e) {
      rethrow(e);
    }
    throw new AssertionError("Unreachable");
  }
}
