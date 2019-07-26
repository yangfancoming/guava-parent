
package com.google.common.testing;
public class TearDownStackTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.testing.testModule";
}
public void testMultipleTearDownsHappenInOrder() throws Exception {
  com.google.common.testing.TearDownStackTest testCase = new com.google.common.testing.TearDownStackTest();
  Throwable failure = null;
  try {
    testCase.testMultipleTearDownsHappenInOrder();
  } catch (Throwable t) {
    failure = t;
  }
  try {
    testCase.tearDown();
  } catch (Throwable t) {
    if (failure == null) {
      failure = t;
    }
  }
  if (failure instanceof Exception) {
    throw (Exception) failure;
  }
  if (failure instanceof Error) {
    throw (Error) failure;
  }
  if (failure != null) {
    throw new RuntimeException(failure);
  }
}

public void testSingleTearDown() throws Exception {
  com.google.common.testing.TearDownStackTest testCase = new com.google.common.testing.TearDownStackTest();
  Throwable failure = null;
  try {
    testCase.testSingleTearDown();
  } catch (Throwable t) {
    failure = t;
  }
  try {
    testCase.tearDown();
  } catch (Throwable t) {
    if (failure == null) {
      failure = t;
    }
  }
  if (failure instanceof Exception) {
    throw (Exception) failure;
  }
  if (failure instanceof Error) {
    throw (Error) failure;
  }
  if (failure != null) {
    throw new RuntimeException(failure);
  }
}

public void testThrowingTearDown() throws Exception {
  com.google.common.testing.TearDownStackTest testCase = new com.google.common.testing.TearDownStackTest();
  Throwable failure = null;
  try {
    testCase.testThrowingTearDown();
  } catch (Throwable t) {
    failure = t;
  }
  try {
    testCase.tearDown();
  } catch (Throwable t) {
    if (failure == null) {
      failure = t;
    }
  }
  if (failure instanceof Exception) {
    throw (Exception) failure;
  }
  if (failure instanceof Error) {
    throw (Error) failure;
  }
  if (failure != null) {
    throw new RuntimeException(failure);
  }
}
}
