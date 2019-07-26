
package com.google.common.base;
public class Utf8Test_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.base.testModule";
}
public void testEncodedLength_invalidStrings() throws Exception {
  com.google.common.base.Utf8Test testCase = new com.google.common.base.Utf8Test();
  testCase.testEncodedLength_invalidStrings();
}

public void testEncodedLength_validStrings() throws Exception {
  com.google.common.base.Utf8Test testCase = new com.google.common.base.Utf8Test();
  testCase.testEncodedLength_validStrings();
}

public void testEncodedLength_validStrings2() throws Exception {
  com.google.common.base.Utf8Test testCase = new com.google.common.base.Utf8Test();
  testCase.testEncodedLength_validStrings2();
}

public void testIsWellFormed_4BytesSamples() throws Exception {
  com.google.common.base.Utf8Test testCase = new com.google.common.base.Utf8Test();
  testCase.testIsWellFormed_4BytesSamples();
}

public void testShardsHaveExpectedRoundTrippables() throws Exception {
  com.google.common.base.Utf8Test testCase = new com.google.common.base.Utf8Test();
  testCase.testShardsHaveExpectedRoundTrippables();
}

public void testSomeSequences() throws Exception {
  com.google.common.base.Utf8Test testCase = new com.google.common.base.Utf8Test();
  testCase.testSomeSequences();
}
}
