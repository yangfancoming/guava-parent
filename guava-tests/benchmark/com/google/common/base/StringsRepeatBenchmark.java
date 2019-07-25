

package com.google.common.base;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;

/**
 * Microbenchmark for {@link com.google.common.base.Strings#repeat}
 *
 * @author Mike Cripps
 */
public class StringsRepeatBenchmark {
  @Param({"1", "5", "25", "125"})
  int count;

  @Param({"1", "10"})
  int length;

  private String originalString;

  @BeforeExperiment
  void setUp() {
    originalString = Strings.repeat("x", length);
  }

  @Benchmark
  void oldRepeat(int reps) {
    for (int i = 0; i < reps; i++) {
      String x = oldRepeat(originalString, count);
      if (x.length() != (originalString.length() * count)) {
        throw new RuntimeException("Wrong length: " + x);
      }
    }
  }

  private static String oldRepeat(String string, int count) {
    // If this multiplication overflows, a NegativeArraySizeException or
    // OutOfMemoryError is not far behind
    final int len = string.length();
    final int size = len * count;
    char[] array = new char[size];
    for (int i = 0; i < size; i += len) {
      string.getChars(0, len, array, i);
    }
    return new String(array);
  }

  @Benchmark
  void mikeRepeat(int reps) {
    for (int i = 0; i < reps; i++) {
      String x = mikeRepeat(originalString, count);
      if (x.length() != (originalString.length() * count)) {
        throw new RuntimeException("Wrong length: " + x);
      }
    }
  }

  private static String mikeRepeat(String string, int count) {
    final int len = string.length();
    char[] strCopy = new char[len * Integer.highestOneBit(count)];
    string.getChars(0, len, strCopy, 0);

    char[] array = new char[len * count];

    int strCopyLen = len;
    int pos = 0;
    while (count != 0) {
      if ((count & 1) != 0) {
        System.arraycopy(strCopy, 0, array, pos, strCopyLen);
        pos += strCopyLen;
      }
      count >>= 1;
      if (count != 0) {
        System.arraycopy(strCopy, 0, strCopy, strCopyLen, strCopyLen);
        strCopyLen <<= 1;
      }
    }
    return new String(array);
  }

  @Benchmark
  void martinRepeat(int reps) {
    for (int i = 0; i < reps; i++) {
      String x = martinRepeat(originalString, count);
      if (x.length() != (originalString.length() * count)) {
        throw new RuntimeException("Wrong length: " + x);
      }
    }
  }

  private static String martinRepeat(String string, int count) {
    final int len = string.length();
    final int size = len * count;
    final char[] array = new char[size];
    string.getChars(0, len, array, 0);
    int n;
    for (n = len; n < size - n; n <<= 1) {
      System.arraycopy(array, 0, array, n, n);
    }
    System.arraycopy(array, 0, array, n, size - n);
    return new String(array);
  }
}
