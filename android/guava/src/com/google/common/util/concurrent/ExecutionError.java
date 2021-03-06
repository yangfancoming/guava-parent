

package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * {@link Error} variant of {@link java.util.concurrent.ExecutionException}. As with {@code
 * ExecutionException}, the error's {@linkplain #getCause() cause} comes from a failed task,
 * possibly run in another thread. That cause should itself be an {@code Error}; if not, use {@code
 * ExecutionException} or {@link UncheckedExecutionException}. This allows the client code to
 * continue to distinguish between exceptions and errors, even when they come from other threads.
 *
 * @author Chris Povirk
 * @since 10.0
 */
@GwtCompatible
public class ExecutionError extends Error {
  /** Creates a new instance with {@code null} as its detail message. */
  protected ExecutionError() {}

  /** Creates a new instance with the given detail message. */
  protected ExecutionError(@NullableDecl String message) {
    super(message);
  }

  /** Creates a new instance with the given detail message and cause. */
  public ExecutionError(@NullableDecl String message, @NullableDecl Error cause) {
    super(message, cause);
  }

  /** Creates a new instance with the given cause. */
  public ExecutionError(@NullableDecl Error cause) {
    super(cause);
  }

  private static final long serialVersionUID = 0;
}
