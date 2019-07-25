

package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;

/**
 * A listening executor service which forwards all its method calls to another listening executor
 * service. Subclasses should override one or more methods to modify the behavior of the backing
 * executor service as desired per the <a
 * href="http://en.wikipedia.org/wiki/Decorator_pattern">decorator pattern</a>.
 *
 * @author Isaac Shum
 * @since 10.0
 */
@CanIgnoreReturnValue // TODO(cpovirk): Consider being more strict.
@GwtIncompatible
public abstract class ForwardingListeningExecutorService extends ForwardingExecutorService
    implements ListeningExecutorService {
  /** Constructor for use by subclasses. */
  protected ForwardingListeningExecutorService() {}

  @Override
  protected abstract ListeningExecutorService delegate();

  @Override
  public <T> ListenableFuture<T> submit(Callable<T> task) {
    return delegate().submit(task);
  }

  @Override
  public ListenableFuture<?> submit(Runnable task) {
    return delegate().submit(task);
  }

  @Override
  public <T> ListenableFuture<T> submit(Runnable task, T result) {
    return delegate().submit(task, result);
  }
}
