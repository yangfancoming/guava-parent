
package com.google.common.escape;
public class EscapersTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.escape.testModule";
}
public void testAsUnicodeEscaper() throws Exception {
  com.google.common.escape.EscapersTest testCase = new com.google.common.escape.EscapersTest();
  testCase.testAsUnicodeEscaper();
}

public void testBuilderCreatesIndependentEscapers() throws Exception {
  com.google.common.escape.EscapersTest testCase = new com.google.common.escape.EscapersTest();
  testCase.testBuilderCreatesIndependentEscapers();
}

public void testBuilderInitialStateNoReplacement() throws Exception {
  com.google.common.escape.EscapersTest testCase = new com.google.common.escape.EscapersTest();
  testCase.testBuilderInitialStateNoReplacement();
}

public void testBuilderInitialStateNoneUnsafe() throws Exception {
  com.google.common.escape.EscapersTest testCase = new com.google.common.escape.EscapersTest();
  testCase.testBuilderInitialStateNoneUnsafe();
}

public void testBuilderRetainsState() throws Exception {
  com.google.common.escape.EscapersTest testCase = new com.google.common.escape.EscapersTest();
  testCase.testBuilderRetainsState();
}

public void testNullEscaper() throws Exception {
  com.google.common.escape.EscapersTest testCase = new com.google.common.escape.EscapersTest();
  testCase.testNullEscaper();
}
}
