
package com.google.common.collect;
public class CountTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.collect.testModule";
}
public void testAddAndGet() throws Exception {
  com.google.common.collect.CountTest testCase = new com.google.common.collect.CountTest();
  testCase.testAddAndGet();
}

public void testGet() throws Exception {
  com.google.common.collect.CountTest testCase = new com.google.common.collect.CountTest();
  testCase.testGet();
}

public void testGetAndAdd() throws Exception {
  com.google.common.collect.CountTest testCase = new com.google.common.collect.CountTest();
  testCase.testGetAndAdd();
}

public void testGetAndSet() throws Exception {
  com.google.common.collect.CountTest testCase = new com.google.common.collect.CountTest();
  testCase.testGetAndSet();
}

public void testSet() throws Exception {
  com.google.common.collect.CountTest testCase = new com.google.common.collect.CountTest();
  testCase.testSet();
}
}
