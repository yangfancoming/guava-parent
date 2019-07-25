

package com.google.common.util.concurrent;

import junit.framework.TestCase;

/**
 * Test for {@link ForwardingCheckedFuture}
 *
 * @author Ben Yu
 */
public class ForwardingCheckedFutureTest extends TestCase {
  public void testForwarding() {
    ForwardingObjectTester.testForwardingObject(ForwardingCheckedFuture.class);
  }
}
