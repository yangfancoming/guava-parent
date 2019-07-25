package com.google.common.util.concurrent;

import junit.framework.TestCase;

/**
 * Tests for {@link ForwardingListenableFuture}.
 *
 * @author Ben Yu
 */
public class ForwardingListenableFutureTest extends TestCase {
  public void testForwarding() {
    ForwardingObjectTester.testForwardingObject(ForwardingListenableFuture.class);
  }
}
