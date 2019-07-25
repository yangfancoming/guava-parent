package com.google.common.util.concurrent;

import junit.framework.TestCase;

/**
 * Test for {@link ForwardingBlockingDeque}
 *
 * @author Emily Soldal
 */
public class ForwardingBlockingDequeTest extends TestCase {

  public void testForwarding() {
    ForwardingObjectTester.testForwardingObject(ForwardingBlockingDeque.class);
  }
}
