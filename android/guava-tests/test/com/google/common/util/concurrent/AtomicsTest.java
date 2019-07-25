

package com.google.common.util.concurrent;

import com.google.common.testing.NullPointerTester;
import java.util.concurrent.atomic.AtomicReferenceArray;
import junit.framework.TestCase;

/**
 * Unit test for {@link Atomics}.
 *
 * @author Kurt Alfred Kluever
 */
public class AtomicsTest extends TestCase {

  private static final Object OBJECT = new Object();

  public void testNewReference() throws Exception {
    assertEquals(null, Atomics.newReference().get());
  }

  public void testNewReference_withInitialValue() throws Exception {
    assertEquals(null, Atomics.newReference(null).get());
    assertEquals(OBJECT, Atomics.newReference(OBJECT).get());
  }

  public void testNewReferenceArray_withLength() throws Exception {
    int length = 42;
    AtomicReferenceArray<String> refArray = Atomics.newReferenceArray(length);
    for (int i = 0; i < length; ++i) {
      assertEquals(null, refArray.get(i));
    }
    try {
      refArray.get(length);
      fail();
    } catch (IndexOutOfBoundsException expected) {
    }
  }

  public void testNewReferenceArray_withNegativeLength() throws Exception {
    try {
      Atomics.newReferenceArray(-1);
      fail();
    } catch (NegativeArraySizeException expected) {
    }
  }

  public void testNewReferenceArray_withStringArray() throws Exception {
    String[] array = {"foo", "bar", "baz"};
    AtomicReferenceArray<String> refArray = Atomics.newReferenceArray(array);
    for (int i = 0; i < array.length; ++i) {
      assertEquals(array[i], refArray.get(i));
    }
    try {
      refArray.get(array.length);
      fail();
    } catch (IndexOutOfBoundsException expected) {
    }
  }

  public void testNewReferenceArray_withNullArray() throws Exception {
    try {
      Atomics.newReferenceArray(null);
      fail();
    } catch (NullPointerException expected) {
    }
  }

  public void testNullPointers() {
    NullPointerTester tester = new NullPointerTester();
    tester.testAllPublicConstructors(Atomics.class); // there aren't any
    tester.testAllPublicStaticMethods(Atomics.class);
  }
}
