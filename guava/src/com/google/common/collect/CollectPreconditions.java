

package com.google.common.collect;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/** Precondition checks useful in collection implementations. */
@GwtCompatible
final class CollectPreconditions {

  static void checkEntryNotNull(Object key, Object value) {
    if (key == null) {
      throw new NullPointerException("null key in entry: null=" + value);
    } else if (value == null) {
      throw new NullPointerException("null value in entry: " + key + "=null");
    }
  }

  @CanIgnoreReturnValue
  static int checkNonnegative(int value, String name) {
    if (value < 0) {
      throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }
    return value;
  }

  @CanIgnoreReturnValue
  static long checkNonnegative(long value, String name) {
    if (value < 0) {
      throw new IllegalArgumentException(name + " cannot be negative but was: " + value);
    }
    return value;
  }

  static void checkPositive(int value, String name) {
    if (value <= 0) {
      throw new IllegalArgumentException(name + " must be positive but was: " + value);
    }
  }

  /**
   * Precondition tester for {@code Iterator.remove()} that throws an exception with a consistent
   * error message.
   */
  static void checkRemove(boolean canRemove) {
    checkState(canRemove, "no calls to next() since the last call to remove()");
  }
}
