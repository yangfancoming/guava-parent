package com.google.common.hash;

/**
 * Abstract interface for objects that can concurrently add longs.
 *
 * @author Louis Wasserman
 */
interface LongAddable {
  void increment();

  void add(long x);

  long sum();
}
