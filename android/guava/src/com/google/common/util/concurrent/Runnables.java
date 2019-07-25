

package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

/**
 * Static utility methods pertaining to the {@link Runnable} interface.
 *
 * @since 16.0
 */
@Beta
@GwtCompatible
public final class Runnables {

  private static final Runnable EMPTY_RUNNABLE =
      new Runnable() {
        @Override
        public void run() {}
      };

  /** Returns a {@link Runnable} instance that does nothing when run. */
  public static Runnable doNothing() {
    return EMPTY_RUNNABLE;
  }

  private Runnables() {}
}
