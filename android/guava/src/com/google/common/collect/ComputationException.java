package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * Wraps an exception that occurred during a computation.
 *
 * @author Bob Lee
 * @since 2.0
 */
@GwtCompatible
public class ComputationException extends RuntimeException {
  /** Creates a new instance with the given cause. */
  public ComputationException(@NullableDecl Throwable cause) {
    super(cause);
  }

  private static final long serialVersionUID = 0;
}
