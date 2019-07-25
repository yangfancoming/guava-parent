

package com.google.common.collect;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Tests the speed of iteration of different iteration methods for collections.
 *
 * @author David Richter
 */
public class IteratorBenchmark {
  @Param({"0", "1", "16", "256", "4096", "65536"})
  int size;

  // use concrete classes to remove any possible polymorphic overhead?
  Object[] array;
  ArrayList<Object> arrayList;
  LinkedList<Object> linkedList;

  @BeforeExperiment
  void setUp() {
    array = new Object[size];
    arrayList = Lists.newArrayListWithCapacity(size);
    linkedList = Lists.newLinkedList();

    for (int i = 0; i < size; i++) {
      Object value = new Object();
      array[i] = value;
      arrayList.add(value);
      linkedList.add(value);
    }
  }

  @Benchmark
  int arrayIndexed(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (int index = 0; index < size; index++) {
        sum += array[index].hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int arrayIndexedLength(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (int index = 0; index < array.length; index++) {
        sum += array[index].hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int arrayFor(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (Object value : array) {
        sum += value.hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int arrayListIndexed(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (int index = 0; index < size; index++) {
        sum += arrayList.get(index).hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int arrayListIndexedLength(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (int index = 0; index < arrayList.size(); index++) {
        sum += arrayList.get(index).hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int arrayListFor(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (Object value : arrayList) {
        sum += value.hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int arrayListForWithHolder(int reps) {
    int[] sumHolder = {0};
    for (int i = 0; i < reps; i++) {
      for (Object value : arrayList) {
        sumHolder[0] += value.hashCode();
      }
    }
    return sumHolder[0];
  }

  @Benchmark
  int arrayListForEachWithHolder(int reps) {
    int[] sumHolder = {0};
    for (int i = 0; i < reps; i++) {
      arrayList.forEach(value -> sumHolder[0] += value.hashCode());
    }
    return sumHolder[0];
  }

  @Benchmark
  int arrayListToArrayFor(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (Object value : arrayList.toArray()) {
        sum += value.hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int linkedListFor(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (Object value : linkedList) {
        sum += value.hashCode();
      }
    }
    return sum;
  }

  @Benchmark
  int linkedListForEach(int reps) {
    int[] sumHolder = {0};
    for (int i = 0; i < reps; i++) {
      linkedList.forEach(value -> sumHolder[0] += value.hashCode());
    }
    return sumHolder[0];
  }

  @Benchmark
  int linkedListToArrayFor(int reps) {
    int sum = 0;
    for (int i = 0; i < reps; i++) {
      for (Object value : linkedList.toArray()) {
        sum += value.hashCode();
      }
    }
    return sum;
  }
}
