package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;

/**
 * Modes for opening a file for writing. The default when mode when none is specified is to truncate
 * the file before writing.
 *
 * @author Colin Decker
 */
@GwtIncompatible
public enum FileWriteMode {
  /** Specifies that writes to the opened file should append to the end of the file. */
  APPEND
}
