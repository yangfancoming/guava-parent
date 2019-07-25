

package com.google.common.reflect;

import com.google.common.testing.EqualsTester;
import com.google.common.testing.NullPointerTester;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import junit.framework.TestCase;

/**
 * Unit test for {@link TypeParameter}.
 *
 * @author Ben Yu
 */
public class TypeParameterTest extends TestCase {

  public <T> void testCaptureTypeParameter() throws Exception {
    TypeVariable<?> variable = new TypeParameter<T>() {}.typeVariable;
    TypeVariable<?> expected =
        TypeParameterTest.class.getDeclaredMethod("testCaptureTypeParameter")
            .getTypeParameters()[0];
    assertEquals(expected, variable);
  }

  public void testConcreteTypeRejected() {
    try {
      new TypeParameter<String>() {};
      fail();
    } catch (IllegalArgumentException expected) {
    }
  }

  public <A, B> void testEquals() throws Exception {
    Method method = TypeParameterTest.class.getDeclaredMethod("testEquals");
    new EqualsTester()
        .addEqualityGroup(new TypeParameter<A>() {}, new TypeParameter<A>() {})
        .addEqualityGroup(new TypeParameter<B>() {})
        .testEquals();
  }

  public void testNullPointers() {
    new NullPointerTester().testAllPublicStaticMethods(TypeParameter.class);
  }
}
