

package com.google.common.collect;

import com.google.common.testing.EqualsTester;
import java.util.Set;
import junit.framework.TestCase;

/**
 * Tests for {@code ForwardingObject}.
 *
 * @author Mike Bostock
 */
public class ForwardingObjectTest extends TestCase {

  public void testEqualsReflexive() {
    final Object delegate = new Object();
    ForwardingObject forward =
        new ForwardingObject() {
          @Override
          protected Object delegate() {
            return delegate;
          }
        };
    new EqualsTester().addEqualityGroup(forward).testEquals();
  }

  public void testEqualsSymmetric() {
    final Set<String> delegate = Sets.newHashSet("foo");
    ForwardingObject forward =
        new ForwardingObject() {
          @Override
          protected Object delegate() {
            return delegate;
          }
        };
    assertEquals(forward.equals(delegate), delegate.equals(forward));
  }
}
