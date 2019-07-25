package com.google.common.base;

import static com.google.common.base.StandardSystemProperty.JAVA_COMPILER;
import static com.google.common.base.StandardSystemProperty.JAVA_EXT_DIRS;
import static com.google.common.truth.Truth.assertWithMessage;

import junit.framework.TestCase;

/**
 * Tests for {@link StandardSystemProperty}.
 *
 * @author Kurt Alfred Kluever
 */
public class StandardSystemPropertyTest extends TestCase {

  public void testGetKeyMatchesString() {
    for (StandardSystemProperty property : StandardSystemProperty.values()) {
      String fieldName = property.name();
      String expected = Ascii.toLowerCase(fieldName).replaceAll("_", ".");
      assertEquals(expected, property.key());
    }
  }

  public void testGetValue() {
    for (StandardSystemProperty property : StandardSystemProperty.values()) {
      assertEquals(System.getProperty(property.key()), property.value());
    }
  }

  public void testToString() {
    for (StandardSystemProperty property : StandardSystemProperty.values()) {
      assertEquals(property.key() + "=" + property.value(), property.toString());
    }
  }

  public void testNoNullValues() {
    for (StandardSystemProperty property : StandardSystemProperty.values()) {
      // Even though the contract in System.getProperties() specifies that a value will exist for
      // all of the listed keys, for some reason the "java.compiler" key returns null in some JVMs.
      if (property == JAVA_COMPILER) {
        continue;
      }
      // Removed in Java 9:
      // https://docs.oracle.com/javase/9/migrate/toc.htm#JSMIG-GUID-2C896CA8-927C-4381-A737-B1D81D964B7B
      if (property == JAVA_EXT_DIRS) {
        continue;
      }
      assertWithMessage(property.toString()).that(property.value()).isNotNull();
    }
  }
}
