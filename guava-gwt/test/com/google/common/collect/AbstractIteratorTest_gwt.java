
package com.google.common.collect;
public class AbstractIteratorTest_gwt extends com.google.gwt.junit.client.GWTTestCase {
@Override public String getModuleName() {
  return "com.google.common.collect.testModule";
}
public void testCantRemove() throws Exception {
  com.google.common.collect.AbstractIteratorTest testCase = new com.google.common.collect.AbstractIteratorTest();
  testCase.testCantRemove();
}

public void testDefaultBehaviorOfNextAndHasNext() throws Exception {
  com.google.common.collect.AbstractIteratorTest testCase = new com.google.common.collect.AbstractIteratorTest();
  testCase.testDefaultBehaviorOfNextAndHasNext();
}

public void testDefaultBehaviorOfPeek() throws Exception {
  com.google.common.collect.AbstractIteratorTest testCase = new com.google.common.collect.AbstractIteratorTest();
  testCase.testDefaultBehaviorOfPeek();
}

public void testDefaultBehaviorOfPeekForEmptyIteration() throws Exception {
  com.google.common.collect.AbstractIteratorTest testCase = new com.google.common.collect.AbstractIteratorTest();
  testCase.testDefaultBehaviorOfPeekForEmptyIteration();
}

public void testException() throws Exception {
  com.google.common.collect.AbstractIteratorTest testCase = new com.google.common.collect.AbstractIteratorTest();
  testCase.testException();
}

public void testExceptionAfterEndOfData() throws Exception {
  com.google.common.collect.AbstractIteratorTest testCase = new com.google.common.collect.AbstractIteratorTest();
  testCase.testExceptionAfterEndOfData();
}

public void testReentrantHasNext() throws Exception {
  com.google.common.collect.AbstractIteratorTest testCase = new com.google.common.collect.AbstractIteratorTest();
  testCase.testReentrantHasNext();
}

public void testSneakyThrow() throws Exception {
  com.google.common.collect.AbstractIteratorTest testCase = new com.google.common.collect.AbstractIteratorTest();
  testCase.testSneakyThrow();
}
}
