package com.google.common.reflect;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.NullPointerTester;
import java.lang.reflect.Method;
import junit.framework.TestCase;

/**
 * Tests for {@link Parameter}.
 *
 * @author Ben Yu
 */
public class ParameterTest extends TestCase {

  public void testNulls() {
    for (Method method : ParameterTest.class.getDeclaredMethods()) {
      for (Parameter param : Invokable.from(method).getParameters()) {
        new NullPointerTester().testAllPublicInstanceMethods(param);
      }
    }
  }

  public void testEquals() {
    EqualsTester tester = new EqualsTester();
    for (Method method : ParameterTest.class.getDeclaredMethods()) {
      for (Parameter param : Invokable.from(method).getParameters()) {
        tester.addEqualityGroup(param);
      }
    }
    tester.testEquals();
  }

  @SuppressWarnings("unused")
  private void someMethod(int i, int j) {}

  @SuppressWarnings("unused")
  private void anotherMethod(int i, String s) {}
}
