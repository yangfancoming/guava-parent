

package com.google.common.base;

import junit.framework.TestCase;

/**
 * Unit test for {@link Defaults}.
 *
 * @author Jige Yu
 */
public class DefaultsTest extends TestCase {
  public void testGetDefaultValue() {
    assertEquals(false, Defaults.defaultValue(boolean.class).booleanValue());
    assertEquals('\0', Defaults.defaultValue(char.class).charValue());
    assertEquals(0, Defaults.defaultValue(byte.class).byteValue());
    assertEquals(0, Defaults.defaultValue(short.class).shortValue());
    assertEquals(0, Defaults.defaultValue(int.class).intValue());
    assertEquals(0, Defaults.defaultValue(long.class).longValue());
    assertEquals(0.0f, Defaults.defaultValue(float.class).floatValue());
    assertEquals(0.0d, Defaults.defaultValue(double.class).doubleValue());
    assertNull(Defaults.defaultValue(void.class));
    assertNull(Defaults.defaultValue(String.class));
  }
}
