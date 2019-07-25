

package com.google.common.collect;

import com.google.caliper.Benchmark;

/**
 * Benchmarking interners.
 *
 * @author Dimitris Andreou
 */
public class InternersBenchmark {
  @Benchmark
  int weakInterner(int reps) {
    Interner<String> interner = Interners.newWeakInterner();
    for (int i = 0; i < reps; i++) {
      interner.intern(Double.toHexString(Math.random()));
    }
    return reps;
  }

  @Benchmark
  int strongInterner(int reps) {
    Interner<String> interner = Interners.newStrongInterner();
    for (int i = 0; i < reps; i++) {
      interner.intern(Double.toHexString(Math.random()));
    }
    return reps;
  }

  @Benchmark
  int stringIntern(int reps) {
    for (int i = 0; i < reps; i++) {
      String unused = Double.toHexString(Math.random()).intern();
    }
    return reps;
  }
}
