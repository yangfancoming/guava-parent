
package com.google.common.util.concurrent;
public class FutureCallbackTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.util.concurrent.testModule";
}
public void testCancel() throws Exception {
  com.google.common.util.concurrent.FutureCallbackTest testCase = new com.google.common.util.concurrent.FutureCallbackTest();
  testCase.testCancel();
}

public void testExecutorSuccess() throws Exception {
  com.google.common.util.concurrent.FutureCallbackTest testCase = new com.google.common.util.concurrent.FutureCallbackTest();
  testCase.testExecutorSuccess();
}

public void testRuntimeExeceptionFromGet() throws Exception {
  com.google.common.util.concurrent.FutureCallbackTest testCase = new com.google.common.util.concurrent.FutureCallbackTest();
  testCase.testRuntimeExeceptionFromGet();
}

public void testSameThreadExecutionException() throws Exception {
  com.google.common.util.concurrent.FutureCallbackTest testCase = new com.google.common.util.concurrent.FutureCallbackTest();
  testCase.testSameThreadExecutionException();
}

public void testSameThreadSuccess() throws Exception {
  com.google.common.util.concurrent.FutureCallbackTest testCase = new com.google.common.util.concurrent.FutureCallbackTest();
  testCase.testSameThreadSuccess();
}

public void testThrowErrorFromGet() throws Exception {
  com.google.common.util.concurrent.FutureCallbackTest testCase = new com.google.common.util.concurrent.FutureCallbackTest();
  testCase.testThrowErrorFromGet();
}

public void testWildcardFuture() throws Exception {
  com.google.common.util.concurrent.FutureCallbackTest testCase = new com.google.common.util.concurrent.FutureCallbackTest();
  testCase.testWildcardFuture();
}
}
