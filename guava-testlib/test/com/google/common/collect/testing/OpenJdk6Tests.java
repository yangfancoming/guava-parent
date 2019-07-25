package com.google.common.collect.testing;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Suite of tests for OpenJdk 6 tests. The existence of this class is a hack because the
 * suitebuilder won't pick up the suites directly in the other classes because they don't extend
 * TestCase. Ergh.
 *
 * @author Kevin Bourrillion
 */
public class OpenJdk6Tests extends TestCase {
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(OpenJdk6SetTests.suite());
    suite.addTest(OpenJdk6ListTests.suite());
    suite.addTest(OpenJdk6QueueTests.suite());
    suite.addTest(OpenJdk6MapTests.suite());
    return suite;
  }
}
