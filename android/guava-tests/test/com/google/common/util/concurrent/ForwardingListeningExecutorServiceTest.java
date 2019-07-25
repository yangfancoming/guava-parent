package com.google.common.util.concurrent;

import junit.framework.TestCase;

/** Unit tests for {@link ForwardingListeningExecutorService} */
public class ForwardingListeningExecutorServiceTest extends TestCase {
  public void testForwarding() {
    ForwardingObjectTester.testForwardingObject(ForwardingListeningExecutorService.class);
  }
}
