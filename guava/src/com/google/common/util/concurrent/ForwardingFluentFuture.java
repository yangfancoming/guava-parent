

package com.google.common.util.concurrent;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * {@link FluentFuture} that forwards all calls to a delegate.
 *
 * <h3>Extension</h3>
 *
 * If you want a class like {@code FluentFuture} but with extra methods, we recommend declaring your
 * own subclass of {@link ListenableFuture}, complete with a method like {@link #from} to adapt an
 * existing {@code ListenableFuture}, implemented atop a {@link ForwardingListenableFuture} that
 * forwards to that future and adds the desired methods.
 */
@GwtCompatible
final class ForwardingFluentFuture<V> extends FluentFuture<V> {
  private final ListenableFuture<V> delegate;

  ForwardingFluentFuture(ListenableFuture<V> delegate) {
    this.delegate = checkNotNull(delegate);
  }

  @Override
  public void addListener(Runnable listener, Executor executor) {
    delegate.addListener(listener, executor);
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    return delegate.cancel(mayInterruptIfRunning);
  }

  @Override
  public boolean isCancelled() {
    return delegate.isCancelled();
  }

  @Override
  public boolean isDone() {
    return delegate.isDone();
  }

  @Override
  public V get() throws InterruptedException, ExecutionException {
    return delegate.get();
  }

  @Override
  public V get(long timeout, TimeUnit unit)
      throws InterruptedException, ExecutionException, TimeoutException {
    return delegate.get(timeout, unit);
  }
}
