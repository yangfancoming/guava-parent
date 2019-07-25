

package com.google.common.collect;

import static com.google.common.collect.DiscreteDomain.integers;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import java.util.Set;

/**
 * Very simple powerSet iteration benchmark.
 *
 * @author Kevin Bourrillion
 */
public class PowerSetBenchmark {
  @Param({"2", "4", "8", "16"})
  int elements;

  Set<Set<Integer>> powerSet;

  @BeforeExperiment
  void setUp() {
    Set<Integer> set = ContiguousSet.create(Range.closed(1, elements), integers());
    powerSet = Sets.powerSet(set);
  }

  @Benchmark
  int iteration(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (Set<Integer> subset : powerSet) {
        for (Integer value : subset) {
          sum += value;
        }
      }
    }
    return sum;
  }
}
