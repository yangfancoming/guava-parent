

package com.google.common.collect;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.common.base.Preconditions;
import java.util.Random;

/**
 * Tests the speed of iteration of different iteration methods for collections.
 *
 * @author David Richter
 */
public class MultisetIteratorBenchmark {
  @Param({"0", "1", "16", "256", "4096", "65536"})
  int size;

  LinkedHashMultiset<Object> linkedHashMultiset;
  HashMultiset<Object> hashMultiset;

  // TreeMultiset requires a Comparable element.
  TreeMultiset<Integer> treeMultiset;

  @BeforeExperiment
  void setUp() {
    hashMultiset = HashMultiset.create(size);
    linkedHashMultiset = LinkedHashMultiset.create(size);
    treeMultiset = TreeMultiset.create();

    Random random = new Random();

    int sizeRemaining = size;

    // TODO(kevinb): generate better test contents for multisets
    for (int i = 0; sizeRemaining > 0; i++) {
      // The JVM will return interned values for small ints.
      Integer value = random.nextInt(1000) + 128;
      int count = Math.min(random.nextInt(10) + 1, sizeRemaining);
      sizeRemaining -= count;
      hashMultiset.add(value, count);
      linkedHashMultiset.add(value, count);
      treeMultiset.add(value, count);
    }

    // TODO(kevinb): convert to assert once benchmark tests enable asserts by default
    Preconditions.checkState(hashMultiset.size() == size);
  }

  @Benchmark
  int hashMultiset(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (Object value : hashMultiset) {
        sum += value.hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int linkedHashMultiset(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (Object value : linkedHashMultiset) {
        sum += value.hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int treeMultiset(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (Object value : treeMultiset) {
        sum += value.hashCode();
      }
    }
    return sum;
  }
}
