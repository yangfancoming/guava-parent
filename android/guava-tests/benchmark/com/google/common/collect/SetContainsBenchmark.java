

package com.google.common.collect;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.common.collect.BenchmarkHelpers.SetImpl;
import com.google.common.collect.CollectionBenchmarkSampleData.Element;
import java.util.Set;

/**
 * A microbenchmark that tests the performance of contains() on various Set implementations.
 *
 * @author Kevin Bourrillion
 */
public class SetContainsBenchmark {
  // Start at 4.88 then multiply by 2*2^phi <evil cackle> - The goal is be uniform
  // yet visit a variety of "values-relative-to-the-next-power-of-2"
  @Param({"5", "30", "180", "1100", "6900", "43000", "260000"}) // "1600000", "9800000"
  private int size;

  // TODO(kevinb): look at exact (==) hits vs. equals() hits?
  @Param({"0.2", "0.8"})
  private double hitRate;

  @Param("true")
  private boolean isUserTypeFast;

  // "" means no fixed seed
  @Param("")
  private SpecialRandom random;

  @Param({"HashSetImpl", "ImmutableSetImpl"})
  private SetImpl impl;

  // the following must be set during setUp
  private Element[] queries;
  private Set<Element> setToTest;

  @BeforeExperiment
  void setUp() {
    CollectionBenchmarkSampleData sampleData =
        new CollectionBenchmarkSampleData(isUserTypeFast, random, hitRate, size);

    this.setToTest = (Set<Element>) impl.create(sampleData.getValuesInSet());
    this.queries = sampleData.getQueries();
  }

  @Benchmark
  boolean contains(int reps) {
    // Paranoia: acting on hearsay that accessing fields might be slow
    // Should write a benchmark to test that!
    Set<Element> set = setToTest;
    Element[] queries = this.queries;

    int mask = queries.length - 1;

    boolean dummy = false;
    for (int i = 0; i < reps; i++) {
      dummy ^= set.contains(queries[i & mask]);
    }
    return dummy;
  }
}
