
package com.google.common.collect;
public class SetOperationsTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.collect.testModule";
}
public void testDifference__MoreTests() throws Exception {
  com.google.common.collect.SetOperationsTest.MoreTests testCase = new com.google.common.collect.SetOperationsTest.MoreTests();
  testCase.setUp();
  testCase.testDifference();
}

public void testIntersection__MoreTests() throws Exception {
  com.google.common.collect.SetOperationsTest.MoreTests testCase = new com.google.common.collect.SetOperationsTest.MoreTests();
  testCase.setUp();
  testCase.testIntersection();
}

public void testSymmetricDifference__MoreTests() throws Exception {
  com.google.common.collect.SetOperationsTest.MoreTests testCase = new com.google.common.collect.SetOperationsTest.MoreTests();
  testCase.setUp();
  testCase.testSymmetricDifference();
}

public void testUnion__MoreTests() throws Exception {
  com.google.common.collect.SetOperationsTest.MoreTests testCase = new com.google.common.collect.SetOperationsTest.MoreTests();
  testCase.setUp();
  testCase.testUnion();
}
}
