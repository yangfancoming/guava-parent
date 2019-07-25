

package com.google.common.base;

import com.google.caliper.Benchmark;

/**
 * Some microbenchmarks for the {@link com.google.common.base.Objects} class.
 *
 * @author Ben L. Titzer
 */
public class ObjectsBenchmark {

  private static final Integer I0 = -45;
  private static final Integer I1 = -1;
  private static final Integer I2 = 3;
  private static final String S0 = "3";
  private static final String S1 = "Ninety five";
  private static final String S2 = "44 one million";
  private static final String S3 = "Lowly laundry lefties";
  private static final String S4 = "89273487U#*&#";
  private static final Double D0 = 9.234d;
  private static final Double D1 = -1.2e55;

  @Benchmark
  int hashString_2(int reps) {
    int dummy = 0;
    for (int i = 0; i < reps; i++) {
      dummy += Objects.hashCode(S0, S1);
    }
    return dummy;
  }

  @Benchmark
  int hashString_3(int reps) {
    int dummy = 0;
    for (int i = 0; i < reps; i++) {
      dummy += Objects.hashCode(S0, S1, S2);
    }
    return dummy;
  }

  @Benchmark
  int hashString_4(int reps) {
    int dummy = 0;
    for (int i = 0; i < reps; i++) {
      dummy += Objects.hashCode(S0, S1, S2, S3);
    }
    return dummy;
  }

  @Benchmark
  int hashString_5(int reps) {
    int dummy = 0;
    for (int i = 0; i < reps; i++) {
      dummy += Objects.hashCode(S0, S1, S2, S3, S4);
    }
    return dummy;
  }

  @Benchmark
  int hashMixed_5(int reps) {
    int dummy = 0;
    for (int i = 0; i < reps; i++) {
      dummy += Objects.hashCode(I2, S1, D1, S2, I0);
      dummy += Objects.hashCode(D0, I1, S3, I2, S0);
    }
    return dummy;
  }
}
