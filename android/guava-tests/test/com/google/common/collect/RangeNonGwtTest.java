package com.google.common.collect;

import com.google.common.testing.NullPointerTester;
import junit.framework.TestCase;

/**
 * Test cases for {@link Range} which cannot run as GWT tests.
 *
 * @author Gregory Kick
 * @see RangeTest
 */
public class RangeNonGwtTest extends TestCase {

  public void testNullPointers() {
    NullPointerTester tester = new NullPointerTester();

    tester.testAllPublicStaticMethods(Range.class);
    tester.testAllPublicStaticMethods(Range.class);

    tester.testAllPublicInstanceMethods(Range.all());
    tester.testAllPublicInstanceMethods(Range.open(1, 3));
  }
}
