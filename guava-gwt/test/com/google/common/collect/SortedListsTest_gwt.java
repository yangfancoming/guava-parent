
package com.google.common.collect;
public class SortedListsTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.collect.testModule";
}
public void testWithDups() throws Exception {
  com.google.common.collect.SortedListsTest testCase = new com.google.common.collect.SortedListsTest();
  testCase.testWithDups();
}

public void testWithoutDups() throws Exception {
  com.google.common.collect.SortedListsTest testCase = new com.google.common.collect.SortedListsTest();
  testCase.testWithoutDups();
}
}
