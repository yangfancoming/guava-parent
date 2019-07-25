package com.google.common.testing;

import java.io.Serializable;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/**
 * Tests for {@link SerializableTester}.
 *
 * @author Nick Kralevich
 */
public class SerializableTesterTest extends TestCase {
  public void testStringAssertions() {
    String original = "hello world";
    String copy = SerializableTester.reserializeAndAssert(original);
    assertEquals(original, copy);
    assertNotSame(original, copy);
  }

  public void testClassWhichDoesNotImplementEquals() {
    ClassWhichDoesNotImplementEquals orig = new ClassWhichDoesNotImplementEquals();
    boolean errorNotThrown = false;
    try {
      SerializableTester.reserializeAndAssert(orig);
      errorNotThrown = true;
    } catch (AssertionFailedError error) {
      // expected
      assertContains("must be Object#equals to", error.getMessage());
    }
    assertFalse(errorNotThrown);
  }

  public void testClassWhichIsAlwaysEqualButHasDifferentHashcodes() {
    ClassWhichIsAlwaysEqualButHasDifferentHashcodes orig =
        new ClassWhichIsAlwaysEqualButHasDifferentHashcodes();
    boolean errorNotThrown = false;
    try {
      SerializableTester.reserializeAndAssert(orig);
      errorNotThrown = true;
    } catch (AssertionFailedError error) {
      // expected
      assertContains("must be equal to the Object#hashCode", error.getMessage());
    }
    assertFalse(errorNotThrown);
  }

  public void testObjectWhichIsEqualButChangesClass() {
    ObjectWhichIsEqualButChangesClass orig = new ObjectWhichIsEqualButChangesClass();
    boolean errorNotThrown = false;
    try {
      SerializableTester.reserializeAndAssert(orig);
      errorNotThrown = true;
    } catch (AssertionFailedError error) {
      // expected
      assertContains("expected:<class ", error.getMessage());
    }
    assertFalse(errorNotThrown);
  }

  private static class ClassWhichDoesNotImplementEquals implements Serializable {
    private static final long serialVersionUID = 1L;
  }

  private static class ClassWhichIsAlwaysEqualButHasDifferentHashcodes implements Serializable {
    private static final long serialVersionUID = 2L;

    @SuppressWarnings("EqualsHashCode")
    @Override
    public boolean equals(Object other) {
      return (other instanceof ClassWhichIsAlwaysEqualButHasDifferentHashcodes);
    }
  }

  private static class ObjectWhichIsEqualButChangesClass implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object other) {
      return (other instanceof ObjectWhichIsEqualButChangesClass || other instanceof OtherForm);
    }

    @Override
    public int hashCode() {
      return 1;
    }

    private Object writeReplace() {
      return new OtherForm();
    }

    private static class OtherForm implements Serializable {
      @Override
      public boolean equals(Object other) {
        return (other instanceof ObjectWhichIsEqualButChangesClass || other instanceof OtherForm);
      }

      @Override
      public int hashCode() {
        return 1;
      }
    }
  }

  private static void assertContains(String expectedSubstring, String actual) {
    // TODO(kevinb): use a Truth assertion here
    if (!actual.contains(expectedSubstring)) {
      fail("expected <" + actual + "> to contain <" + expectedSubstring + ">");
    }
  }
}
