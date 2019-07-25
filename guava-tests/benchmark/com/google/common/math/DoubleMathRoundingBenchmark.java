

package com.google.common.math;

import static com.google.common.math.MathBenchmarking.ARRAY_MASK;
import static com.google.common.math.MathBenchmarking.ARRAY_SIZE;
import static com.google.common.math.MathBenchmarking.randomDouble;
import static com.google.common.math.MathBenchmarking.randomPositiveDouble;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import java.math.RoundingMode;

/**
 * Benchmarks for the rounding methods of {@code DoubleMath}.
 *
 * @author Louis Wasserman
 */
public class DoubleMathRoundingBenchmark {
  private static final double[] doubleInIntRange = new double[ARRAY_SIZE];
  private static final double[] doubleInLongRange = new double[ARRAY_SIZE];
  private static final double[] positiveDoubles = new double[ARRAY_SIZE];

  @Param({"DOWN", "UP", "FLOOR", "CEILING", "HALF_EVEN", "HALF_UP", "HALF_DOWN"})
  RoundingMode mode;

  @BeforeExperiment
  void setUp() {
    for (int i = 0; i < ARRAY_SIZE; i++) {
      doubleInIntRange[i] = randomDouble(Integer.SIZE - 2);
      doubleInLongRange[i] = randomDouble(Long.SIZE - 2);
      positiveDoubles[i] = randomPositiveDouble();
    }
  }

  @Benchmark
  int roundToInt(int reps) {
    int tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += DoubleMath.roundToInt(doubleInIntRange[j], mode);
    }
    return tmp;
  }

  @Benchmark
  long roundToLong(int reps) {
    long tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += DoubleMath.roundToLong(doubleInLongRange[j], mode);
    }
    return tmp;
  }

  @Benchmark
  int roundToBigInteger(int reps) {
    int tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += DoubleMath.roundToBigInteger(positiveDoubles[j], mode).intValue();
    }
    return tmp;
  }

  @Benchmark
  int log2Round(int reps) {
    int tmp = 0;
    for (int i = 0; i < reps; i++) {
      int j = i & ARRAY_MASK;
      tmp += DoubleMath.log2(positiveDoubles[j], mode);
    }
    return tmp;
  }
}
