
package com.google.common.net;
public class UrlEscapersTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.net.testModule";
}
public void testUrlFormParameterEscaper() throws Exception {
  com.google.common.net.UrlEscapersTest testCase = new com.google.common.net.UrlEscapersTest();
  testCase.testUrlFormParameterEscaper();
}

public void testUrlFragmentEscaper() throws Exception {
  com.google.common.net.UrlEscapersTest testCase = new com.google.common.net.UrlEscapersTest();
  testCase.testUrlFragmentEscaper();
}

public void testUrlPathSegmentEscaper() throws Exception {
  com.google.common.net.UrlEscapersTest testCase = new com.google.common.net.UrlEscapersTest();
  testCase.testUrlPathSegmentEscaper();
}
}
