package com.google.common.util.concurrent;

import com.google.common.collect.ForwardingObject;
import junit.framework.TestCase;

/**
 * Tests for {@link ForwardingObjectTester}.
 *
 * @author Ben Yu
 */
public class ForwardingObjectTesterTest extends TestCase {

  public void testFailsToForward() {
    try {
      ForwardingObjectTester.testForwardingObject(FailToForward.class);
    } catch (AssertionError | UnsupportedOperationException expected) {
      // UnsupportedOperationException is what we see on Android.
      return;
    }
    fail("Should have thrown");
  }

  @AndroidIncompatible // TODO(cpovirk): java.lang.IllegalAccessError: superclass not accessible
  public void testSuccessfulForwarding() {
    ForwardingObjectTester.testForwardingObject(ForwardToDelegate.class);
  }

  private abstract static class FailToForward extends ForwardingObject implements Runnable {
    @Override
    public void run() {}
  }

  private abstract static class ForwardToDelegate extends ForwardingObject implements Runnable {
    @Override
    public void run() {
      delegate().run();
    }

    @Override
    protected abstract Runnable delegate();
  }
}
