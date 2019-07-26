
package com.google.common.collect;
public class MapMakerTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.collect.testModule";
}
public void testInitialCapacity_negative__MakerTest() throws Exception {
  com.google.common.collect.MapMakerTest.MakerTest testCase = new com.google.common.collect.MapMakerTest.MakerTest();
  testCase.testInitialCapacity_negative();
}

public void testReturnsPlainConcurrentHashMapWhenPossible__MakerTest() throws Exception {
  com.google.common.collect.MapMakerTest.MakerTest testCase = new com.google.common.collect.MapMakerTest.MakerTest();
  testCase.testReturnsPlainConcurrentHashMapWhenPossible();
}
}
