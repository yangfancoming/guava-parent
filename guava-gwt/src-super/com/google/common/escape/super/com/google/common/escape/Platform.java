package com.google.common.escape;

/** @author Jesse Wilson */
final class Platform {

  private static final char[] CHAR_BUFFER = new char[1024];

  static char[] charBufferFromThreadLocal() {
    // ThreadLocal is not available to GWT, so we always reuse the same
    // instance.  It is always safe to return the same instance because
    // javascript is single-threaded, and only used by blocks that doesn't
    // involve async callbacks.
    return CHAR_BUFFER;
  }

  private Platform() {}
}
