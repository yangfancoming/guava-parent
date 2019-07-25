package com.google.common.util.concurrent;

import junit.framework.TestCase;

/** Unit tests for {@link ForwardingExecutorService} */
public class ForwardingExecutorServiceTest extends TestCase {
  public void testForwarding() {
    ForwardingObjectTester.testForwardingObject(ForwardingExecutorService.class);
  }
}
