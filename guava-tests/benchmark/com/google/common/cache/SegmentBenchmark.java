

package com.google.common.cache;

import static com.google.common.base.Preconditions.checkState;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.common.cache.LocalCache.Segment;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Benchmark for {@code LocalCache.Segment.expand()}.
 *
 * @author Charles Fry
 */
public class SegmentBenchmark {

  @Param({"16", "32", "64", "128", "256", "512", "1024", "2048", "4096", "8192"})
  int capacity;

  private Segment<Object, Object> segment;

  @BeforeExperiment
  void setUp() {
    LocalCache<Object, Object> cache =
        new LocalCache<>(
            CacheBuilder.newBuilder().concurrencyLevel(1).initialCapacity(capacity), null);
    checkState(cache.segments.length == 1);
    segment = cache.segments[0];
    checkState(segment.table.length() == capacity);
    for (int i = 0; i < segment.threshold; i++) {
      cache.put(new Object(), new Object());
    }
    checkState(segment.table.length() == capacity);
  }

  @Benchmark
  int time(int reps) {
    int dummy = 0;
    AtomicReferenceArray<ReferenceEntry<Object, Object>> oldTable = segment.table;
    for (int i = 0; i < reps; i++) {
      segment.expand();
      segment.table = oldTable;
      dummy += segment.count;
    }
    return dummy;
  }
}
