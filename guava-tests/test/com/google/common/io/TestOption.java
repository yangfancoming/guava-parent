package com.google.common.io;

/**
 * Options controlling the behavior of sources/sinks/streams for testing.
 *
 * @author Colin Decker
 */
public enum TestOption {
  OPEN_THROWS,
  SKIP_THROWS,
  READ_THROWS,
  WRITE_THROWS,
  CLOSE_THROWS,
  AVAILABLE_ALWAYS_ZERO
}
