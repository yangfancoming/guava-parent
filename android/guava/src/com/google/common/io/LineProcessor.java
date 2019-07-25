

package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;

/**
 * A callback to be used with the streaming {@code readLines} methods.
 *
 * <p>{@link #processLine} will be called for each line that is read, and should return {@code
 * false} when you want to stop processing.
 *
 * @author Miles Barr
 * @since 1.0
 */
@Beta
@GwtIncompatible
public interface LineProcessor<T> {

  /**
   * This method will be called once for each line.
   *
   * @param line the line read from the input, without delimiter
   * @return true to continue processing, false to stop
   */
  @CanIgnoreReturnValue // some uses know that their processor never returns false
  boolean processLine(String line) throws IOException;

  /** Return the result of processing all the lines. */
  T getResult();
}
