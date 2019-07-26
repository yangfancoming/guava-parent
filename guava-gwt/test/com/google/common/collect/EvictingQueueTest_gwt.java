
package com.google.common.collect;
public class EvictingQueueTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.collect.testModule";
}
public void testAddAll() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testAddAll();
}

public void testAddAll_largeList() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testAddAll_largeList();
}

public void testCreateWithNegativeSize() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testCreateWithNegativeSize();
}

public void testCreateWithZeroSize() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testCreateWithZeroSize();
}

public void testEvictingAfterOne() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testEvictingAfterOne();
}

public void testEvictingAfterThree() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testEvictingAfterThree();
}

public void testRemainingCapacity_maxSize0() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testRemainingCapacity_maxSize0();
}

public void testRemainingCapacity_maxSize1() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testRemainingCapacity_maxSize1();
}

public void testRemainingCapacity_maxSize3() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testRemainingCapacity_maxSize3();
}

public void testSerialization() throws Exception {
  com.google.common.collect.EvictingQueueTest testCase = new com.google.common.collect.EvictingQueueTest();
  testCase.testSerialization();
}
}
