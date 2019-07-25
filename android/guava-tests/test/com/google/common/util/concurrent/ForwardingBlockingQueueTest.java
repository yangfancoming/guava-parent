package com.google.common.util.concurrent;

import junit.framework.TestCase;

/** Unit tests for {@link ForwardingBlockingQueue} */
public class ForwardingBlockingQueueTest extends TestCase {
  public void testForwarding() {
    ForwardingObjectTester.testForwardingObject(ForwardingBlockingQueue.class);
  }
}
