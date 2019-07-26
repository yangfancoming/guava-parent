
package com.google.common.escape;
public class UnicodeEscaperTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.escape.testModule";
}
public void testBadStrings() throws Exception {
  com.google.common.escape.UnicodeEscaperTest testCase = new com.google.common.escape.UnicodeEscaperTest();
  testCase.testBadStrings();
}

public void testCodePointAt_IndexOutOfBoundsException() throws Exception {
  com.google.common.escape.UnicodeEscaperTest testCase = new com.google.common.escape.UnicodeEscaperTest();
  testCase.testCodePointAt_IndexOutOfBoundsException();
}

public void testFalsePositivesForNextEscapedIndex() throws Exception {
  com.google.common.escape.UnicodeEscaperTest testCase = new com.google.common.escape.UnicodeEscaperTest();
  testCase.testFalsePositivesForNextEscapedIndex();
}

public void testGrowBuffer() throws Exception {
  com.google.common.escape.UnicodeEscaperTest testCase = new com.google.common.escape.UnicodeEscaperTest();
  testCase.testGrowBuffer();
}

public void testNopEscaper() throws Exception {
  com.google.common.escape.UnicodeEscaperTest testCase = new com.google.common.escape.UnicodeEscaperTest();
  testCase.testNopEscaper();
}

public void testNullInput() throws Exception {
  com.google.common.escape.UnicodeEscaperTest testCase = new com.google.common.escape.UnicodeEscaperTest();
  testCase.testNullInput();
}

public void testSimpleEscaper() throws Exception {
  com.google.common.escape.UnicodeEscaperTest testCase = new com.google.common.escape.UnicodeEscaperTest();
  testCase.testSimpleEscaper();
}

public void testSurrogatePairs() throws Exception {
  com.google.common.escape.UnicodeEscaperTest testCase = new com.google.common.escape.UnicodeEscaperTest();
  testCase.testSurrogatePairs();
}

public void testTrailingHighSurrogate() throws Exception {
  com.google.common.escape.UnicodeEscaperTest testCase = new com.google.common.escape.UnicodeEscaperTest();
  testCase.testTrailingHighSurrogate();
}
}
