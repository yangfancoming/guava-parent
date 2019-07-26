
package com.google.common.escape;
public class ArrayBasedUnicodeEscaperTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.escape.testModule";
}
public void testCodePointsFromSurrogatePairs() throws Exception {
  com.google.common.escape.ArrayBasedUnicodeEscaperTest testCase = new com.google.common.escape.ArrayBasedUnicodeEscaperTest();
  testCase.testCodePointsFromSurrogatePairs();
}

public void testDeleteUnsafeChars() throws Exception {
  com.google.common.escape.ArrayBasedUnicodeEscaperTest testCase = new com.google.common.escape.ArrayBasedUnicodeEscaperTest();
  testCase.testDeleteUnsafeChars();
}

public void testReplacementPriority() throws Exception {
  com.google.common.escape.ArrayBasedUnicodeEscaperTest testCase = new com.google.common.escape.ArrayBasedUnicodeEscaperTest();
  testCase.testReplacementPriority();
}

public void testReplacements() throws Exception {
  com.google.common.escape.ArrayBasedUnicodeEscaperTest testCase = new com.google.common.escape.ArrayBasedUnicodeEscaperTest();
  testCase.testReplacements();
}

public void testSafeRange() throws Exception {
  com.google.common.escape.ArrayBasedUnicodeEscaperTest testCase = new com.google.common.escape.ArrayBasedUnicodeEscaperTest();
  testCase.testSafeRange();
}
}
