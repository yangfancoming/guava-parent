

package com.google.common.cache;

import com.google.caliper.AfterExperiment;
import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.common.primitives.Ints;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Single-threaded benchmark for {@link LoadingCache}.
 *
 * @author Charles Fry
 */
public class LoadingCacheSingleThreadBenchmark {
  @Param({"1000", "2000"})
  int maximumSize;

  @Param("5000")
  int distinctKeys;

  @Param("4")
  int segments;

  // 1 means uniform likelihood of keys; higher means some keys are more popular
  // tweak this to control hit rate
  @Param("2.5")
  double concentration;

  Random random = new Random();

  LoadingCache<Integer, Integer> cache;

  int max;

  static AtomicLong requests = new AtomicLong(0);
  static AtomicLong misses = new AtomicLong(0);

  @BeforeExperiment
  void setUp() {
    // random integers will be generated in this range, then raised to the
    // power of (1/concentration) and floor()ed
    max = Ints.checkedCast((long) Math.pow(distinctKeys, concentration));

    cache =
        CacheBuilder.newBuilder()
            .concurrencyLevel(segments)
            .maximumSize(maximumSize)
            .build(
                new CacheLoader<Integer, Integer>() {
                  @Override
                  public Integer load(Integer from) {
                    return (int) misses.incrementAndGet();
                  }
                });

    // To start, fill up the cache.
    // Each miss both increments the counter and causes the map to grow by one,
    // so until evictions begin, the size of the map is the greatest return
    // value seen so far
    while (cache.getUnchecked(nextRandomKey()) < maximumSize) {}

    requests.set(0);
    misses.set(0);
  }

  @Benchmark
  int time(int reps) {
    int dummy = 0;
    for (int i = 0; i < reps; i++) {
      dummy += cache.getUnchecked(nextRandomKey());
    }
    requests.addAndGet(reps);
    return dummy;
  }

  private int nextRandomKey() {
    int a = random.nextInt(max);

    /*
     * For example, if concentration=2.0, the following takes the square root of
     * the uniformly-distributed random integer, then truncates any fractional
     * part, so higher integers would appear (in this case linearly) more often
     * than lower ones.
     */
    return (int) Math.pow(a, 1.0 / concentration);
  }

  @AfterExperiment
  void tearDown() {
    double req = requests.get();
    double hit = req - misses.get();

    // Currently, this is going into /dev/null, but I'll fix that
    System.out.println("hit rate: " + hit / req);
  }

  // for proper distributions later:
  // import JSci.maths.statistics.ProbabilityDistribution;
  // int key = (int) dist.inverse(random.nextDouble());
}
