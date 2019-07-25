package com.google.common.collect;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.common.collect.BenchmarkHelpers.SetImpl;

/**
 * This is meant to be used with {@code --measureMemory} to measure the memory usage of various
 * {@code Set} implementations.
 *
 * @author Christopher Swenson
 */
public class SetCreationBenchmark {
  @Param({
    "3", "6", "11", "23", "45", "91", "181", "362", "724", "1448", "2896", "5793", "11585", "23170",
    "46341", "92682", "185364", "370728", "741455", "1482910", "2965821", "5931642"
  })
  private int size;

  // "" means no fixed seed
  @Param("1234")
  private SpecialRandom random;

  @Param({"ImmutableSetImpl", "HashSetImpl"})
  private SetImpl impl;

  // the following must be set during setUp
  private CollectionBenchmarkSampleData sampleData;

  @BeforeExperiment
  void setUp() {
    sampleData = new CollectionBenchmarkSampleData(true, random, 0.8, size);
  }

  @Benchmark
  int creation(int reps) {
    int x = 0;
    for (int i = 0; i < reps; i++) {
      x ^= System.identityHashCode(impl.create(sampleData.getValuesInSet()));
    }
    return x;
  }
}
