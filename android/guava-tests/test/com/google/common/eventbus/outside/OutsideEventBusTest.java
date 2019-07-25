

package com.google.common.eventbus.outside;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import junit.framework.TestCase;

/**
 * Test cases for {@code EventBus} that must not be in the same package.
 *
 * @author Louis Wasserman
 */
public class OutsideEventBusTest extends TestCase {

  /*
   * If you do this test from common.eventbus.EventBusTest, it doesn't actually test the behavior.
   * That is, even if exactly the same method works from inside the common.eventbus package tests,
   * it can fail here.
   */
  public void testAnonymous() {
    final AtomicReference<String> holder = new AtomicReference<>();
    final AtomicInteger deliveries = new AtomicInteger();
    EventBus bus = new EventBus();
    bus.register(
        new Object() {
          @Subscribe
          public void accept(String str) {
            holder.set(str);
            deliveries.incrementAndGet();
          }
        });

    String EVENT = "Hello!";
    bus.post(EVENT);

    assertEquals("Only one event should be delivered.", 1, deliveries.get());
    assertEquals("Correct string should be delivered.", EVENT, holder.get());
  }
}
