package com.google.common.cache;

import com.google.common.testing.EqualsTester;
import junit.framework.TestCase;

/**
 * Unit tests of {@link RemovalNotification}.
 *
 * @author Ben Yu
 */
public class RemovalNotificationTest extends TestCase {

  public void testEquals() {
    new EqualsTester()
        .addEqualityGroup(
            RemovalNotification.create("one", 1, RemovalCause.EXPLICIT),
            RemovalNotification.create("one", 1, RemovalCause.REPLACED))
        .addEqualityGroup(RemovalNotification.create("1", 1, RemovalCause.EXPLICIT))
        .addEqualityGroup(RemovalNotification.create("one", 2, RemovalCause.EXPLICIT))
        .testEquals();
  }
}
