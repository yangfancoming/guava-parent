package com.google.common.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.PatternSyntaxException;
import junit.framework.TestCase;

/**
 * Unit test for {@link PatternFilenameFilter}.
 *
 * @author Chris Nokleberg
 */
public class PatternFilenameFilterTest extends TestCase {

  public void testSyntaxException() {
    try {
      new PatternFilenameFilter("(");
      fail("expected exception");
    } catch (PatternSyntaxException expected) {
    }
  }

  public void testAccept() {
    File dir = new File("foo");
    FilenameFilter filter = new PatternFilenameFilter("a+");
    assertTrue(filter.accept(dir, "a"));
    assertTrue(filter.accept(dir, "aaaa"));
    assertFalse(filter.accept(dir, "b"));

    // Show that dir is ignored
    assertTrue(filter.accept(null, "a"));
  }
}
