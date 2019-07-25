

package com.google.common.math;

import static com.google.common.math.MathBenchmarking.ARRAY_MASK;
import static com.google.common.math.MathBenchmarking.ARRAY_SIZE;
import static com.google.common.math.MathBenchmarking.RANDOM_SOURCE;
import static com.google.common.math.MathBenchmarking.randomNonZeroBigInteger;
import static com.google.common.math.MathBenchmarking.randomPositiveBigInteger;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import java.math.RoundingMode;

/**
 * Benchmarks for the rounding methods of {@code IntMath}.
 *
 * @author Louis Wasserman
 */
public class IntMathRoundingBenchmark {
  private static final int[] positive = new int[ARRAY_SIZE];
  private static final int[] nonzero = new int[ARRAY_SIZE];
  private static final int[] ints = new int[ARRAY_SIZE];

  @BeforeExperiment
  void setUp() {
    for (int i = 0; i < ARRAY_SIZE; i++) {
      positive[i] = randomPositiveBigInteger(Integer.SIZE - 2).intValue();
      nonzero[i] = randomNonZeroBigInteger(Integer.SIZE - 2).intValue();
      ints[i] = RANDOM_SOURCE.nextInt();
    }
  }

  @Param({"DOWN", "UP", "FLOOR", "CEILING", "HALF_EVEN", "HALF_UP", "HALF_DOWN"})
  RoundingMode mode;

  @Benchmark
  int log2(int reps) {
    int tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += IntMath.log2(positive[j], mode);
    }
    return tmp;
  }

  @Benchmark
  int log10(int reps) {
    int tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += IntMath.log10(positive[j], mode);
    }
    return tmp;
  }

  @Benchmark
  int sqrt(int reps) {
    int tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += IntMath.sqrt(positive[j], mode);
    }
    return tmp;
  }

  @Benchmark
  int divide(int reps) {
    int tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += IntMath.divide(ints[j], nonzero[j], mode);
    }
    return tmp;
  }
}
