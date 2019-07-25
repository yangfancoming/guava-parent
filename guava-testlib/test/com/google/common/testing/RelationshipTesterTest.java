package com.google.common.testing;

import com.google.common.testing.RelationshipTester.ItemReporter;
import junit.framework.TestCase;

/**
 * Tests for {@link RelationshipTester}.
 *
 * @author Ben Yu
 */
public class RelationshipTesterTest extends TestCase {

  public void testNulls() {
    new ClassSanityTester()
        .setDefault(ItemReporter.class, new ItemReporter())
        .testNulls(RelationshipTester.class);
  }
}
