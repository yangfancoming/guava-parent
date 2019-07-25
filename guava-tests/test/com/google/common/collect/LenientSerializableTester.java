

package com.google.common.collect;

import static com.google.common.testing.SerializableTester.reserialize;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.testing.SerializableTester;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;

/**
 * Variant of {@link SerializableTester} that does not require the reserialized object's class to be
 * identical to the original.
 *
 * @author Chris Povirk
 */
/*
 * The whole thing is really @GwtIncompatible, but GwtJUnitConvertedTestModule doesn't have a
 * parameter for non-GWT, non-test files, and it didn't seem worth adding one for this unusual case.
 */
@GwtCompatible(emulated = true)
final class LenientSerializableTester {
  /*
   * TODO(cpovirk): move this to c.g.c.testing if we allow for c.g.c.annotations dependencies so
   * that it can be GWTified?
   */
  @CanIgnoreReturnValue
  @GwtIncompatible // SerializableTester
  static <E> Set<E> reserializeAndAssertLenient(Set<E> original) {
    Set<E> copy = reserialize(original);
    assertEquals(original, copy);
    assertTrue(copy instanceof ImmutableSet);
    return copy;
  }

  @CanIgnoreReturnValue
  @GwtIncompatible // SerializableTester
  static <E> Multiset<E> reserializeAndAssertLenient(Multiset<E> original) {
    Multiset<E> copy = reserialize(original);
    assertEquals(original, copy);
    assertTrue(copy instanceof ImmutableMultiset);
    return copy;
  }

  private LenientSerializableTester() {}
}
