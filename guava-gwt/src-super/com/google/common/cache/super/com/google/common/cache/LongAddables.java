package com.google.common.cache;

/**
 * GWT emulation for LongAddables.
 *
 * @author Louis Wasserman
 */
final class LongAddables {
  public static LongAddable create() {
    return new LongAdder();
  }
}
