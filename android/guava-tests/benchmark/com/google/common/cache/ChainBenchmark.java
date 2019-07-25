

package com.google.common.cache;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.common.cache.LocalCache.Segment;

/**
 * Benchmark for {@code LocalCache.Segment.removeEntryFromChain}.
 *
 * @author Charles Fry
 */
public class ChainBenchmark {

  @Param({"1", "2", "3", "4", "5", "6"})
  int length;

  private Segment<Object, Object> segment;
  private ReferenceEntry<Object, Object> head;
  private ReferenceEntry<Object, Object> chain;

  @BeforeExperiment
  void setUp() {
    LocalCache<Object, Object> cache =
        new LocalCache<>(CacheBuilder.newBuilder().concurrencyLevel(1), null);
    segment = cache.segments[0];
    chain = null;
    for (int i = 0; i < length; i++) {
      Object key = new Object();
      chain = segment.newEntry(key, cache.hash(key), chain);
      if (i == 0) {
        head = chain;
      }
    }
  }

  @Benchmark
  int time(int reps) {
    int dummy = 0;
    for (int i = 0; i < reps; i++) {
      segment.removeEntryFromChain(chain, head);
      dummy += segment.count;
    }
    return dummy;
  }
}
