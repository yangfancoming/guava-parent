package com.google.common.hash;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import java.security.MessageDigest;

/**
 * Benchmarks for comparing instance creation of {@link MessageDigest}s.
 *
 * @author Kurt Alfred Kluever
 */
public class MessageDigestCreationBenchmark {

  @Param({"MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512"})
  private String algorithm;

  private MessageDigest md;

  @BeforeExperiment
  void setUp() throws Exception {
    md = MessageDigest.getInstance(algorithm);
  }

  @Benchmark
  int getInstance(int reps) throws Exception {
    int retValue = 0;
    for (int i = 0; i < reps; i++) {
      retValue ^= MessageDigest.getInstance(algorithm).getDigestLength();
    }
    return retValue;
  }

  @Benchmark
  int clone(int reps) throws Exception {
    int retValue = 0;
    for (int i = 0; i < reps; i++) {
      retValue ^= ((MessageDigest) md.clone()).getDigestLength();
    }
    return retValue;
  }
}
