
package com.google.common.base;
public class AbstractIteratorTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.base.testModule";
}
public void testCantRemove() throws Exception {
  com.google.common.base.AbstractIteratorTest testCase = new com.google.common.base.AbstractIteratorTest();
  testCase.testCantRemove();
}

public void testDefaultBehaviorOfNextAndHasNext() throws Exception {
  com.google.common.base.AbstractIteratorTest testCase = new com.google.common.base.AbstractIteratorTest();
  testCase.testDefaultBehaviorOfNextAndHasNext();
}

public void testException() throws Exception {
  com.google.common.base.AbstractIteratorTest testCase = new com.google.common.base.AbstractIteratorTest();
  testCase.testException();
}

public void testExceptionAfterEndOfData() throws Exception {
  com.google.common.base.AbstractIteratorTest testCase = new com.google.common.base.AbstractIteratorTest();
  testCase.testExceptionAfterEndOfData();
}

public void testReentrantHasNext() throws Exception {
  com.google.common.base.AbstractIteratorTest testCase = new com.google.common.base.AbstractIteratorTest();
  testCase.testReentrantHasNext();
}

public void testSneakyThrow() throws Exception {
  com.google.common.base.AbstractIteratorTest testCase = new com.google.common.base.AbstractIteratorTest();
  testCase.testSneakyThrow();
}
}
