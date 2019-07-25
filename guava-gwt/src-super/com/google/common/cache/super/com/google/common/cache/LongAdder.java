package com.google.common.cache;

/**
 * GWT emulated version of LongAdder.
 *
 * @author Charles Fry
 */
class LongAdder implements LongAddable {

  private long value;

  public void increment() {
    value++;
  }

  public void add(long x) {
    value += x;
  }

  public long sum() {
    return value;
  }
}
