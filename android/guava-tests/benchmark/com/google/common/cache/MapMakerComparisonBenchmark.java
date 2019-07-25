package com.google.common.cache;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.common.collect.MapMaker;
import java.util.Map;

/**
 * Compare CacheBuilder and MapMaker performance, ensuring that they remain on par with each other.
 *
 * @author Nikita Sidorov
 */
public class MapMakerComparisonBenchmark {
  private static final String TEST_KEY = "test key";
  private static final String TEST_VALUE = "test value";

  // Non-loading versions:
  private final Map<Object, Object> map = new MapMaker().makeMap(); // Returns ConcurrentHashMap
  private final Cache<Object, Object> cache = CacheBuilder.newBuilder().recordStats().build();
  private final Cache<Object, Object> cacheNoStats = CacheBuilder.newBuilder().build();

  @BeforeExperiment
  void setUp() {
    map.put(TEST_KEY, TEST_VALUE);
    cache.put(TEST_KEY, TEST_VALUE);
    cacheNoStats.put(TEST_KEY, TEST_VALUE);
  }

  @Benchmark
  void concurrentHashMap(int rep) {
    for (int i = 0; i < rep; i++) {
      map.get(TEST_KEY);
    }
  }

  @Benchmark
  void cacheBuilder_stats(int rep) {
    for (int i = 0; i < rep; i++) {
      cache.getIfPresent(TEST_KEY);
    }
  }

  @Benchmark
  void cacheBuilder(int rep) {
    for (int i = 0; i < rep; i++) {
      cacheNoStats.getIfPresent(TEST_KEY);
    }
  }
}
