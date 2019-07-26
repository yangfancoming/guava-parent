
package com.google.common.escape;
public class ArrayBasedCharEscaperTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.escape.testModule";
}
public void testDeleteUnsafeChars() throws Exception {
  com.google.common.escape.ArrayBasedCharEscaperTest testCase = new com.google.common.escape.ArrayBasedCharEscaperTest();
  testCase.testDeleteUnsafeChars();
}

public void testReplacementPriority() throws Exception {
  com.google.common.escape.ArrayBasedCharEscaperTest testCase = new com.google.common.escape.ArrayBasedCharEscaperTest();
  testCase.testReplacementPriority();
}

public void testSafeRange() throws Exception {
  com.google.common.escape.ArrayBasedCharEscaperTest testCase = new com.google.common.escape.ArrayBasedCharEscaperTest();
  testCase.testSafeRange();
}

public void testSafeRange_maxLessThanMin() throws Exception {
  com.google.common.escape.ArrayBasedCharEscaperTest testCase = new com.google.common.escape.ArrayBasedCharEscaperTest();
  testCase.testSafeRange_maxLessThanMin();
}
}
