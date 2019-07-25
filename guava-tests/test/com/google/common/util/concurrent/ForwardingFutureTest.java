package com.google.common.util.concurrent;

import junit.framework.TestCase;

/** Unit tests for {@link ForwardingFuture} */
public class ForwardingFutureTest extends TestCase {
  public void testForwarding() {
    ForwardingObjectTester.testForwardingObject(ForwardingFuture.class);
  }
}
