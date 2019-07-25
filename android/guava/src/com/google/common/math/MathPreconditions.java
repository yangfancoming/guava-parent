

package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * A collection of preconditions for math functions.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@CanIgnoreReturnValue
final class MathPreconditions {
  static int checkPositive(@NullableDecl String role, int x) {
    if (x <= 0) {
      throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
    }
    return x;
  }

  static long checkPositive(@NullableDecl String role, long x) {
    if (x <= 0) {
      throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
    }
    return x;
  }

  static BigInteger checkPositive(@NullableDecl String role, BigInteger x) {
    if (x.signum() <= 0) {
      throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
    }
    return x;
  }

  static int checkNonNegative(@NullableDecl String role, int x) {
    if (x < 0) {
      throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
    }
    return x;
  }

  static long checkNonNegative(@NullableDecl String role, long x) {
    if (x < 0) {
      throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
    }
    return x;
  }

  static BigInteger checkNonNegative(@NullableDecl String role, BigInteger x) {
    if (x.signum() < 0) {
      throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
    }
    return x;
  }

  static double checkNonNegative(@NullableDecl String role, double x) {
    if (!(x >= 0)) { // not x < 0, to work with NaN.
      throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
    }
    return x;
  }

  static void checkRoundingUnnecessary(boolean condition) {
    if (!condition) {
      throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
    }
  }

  static void checkInRangeForRoundingInputs(boolean condition, double input, RoundingMode mode) {
    if (!condition) {
      throw new ArithmeticException(
          "rounded value is out of range for input " + input + " and rounding mode " + mode);
    }
  }

  static void checkNoOverflow(boolean condition, String methodName, int a, int b) {
    if (!condition) {
      throw new ArithmeticException("overflow: " + methodName + "(" + a + ", " + b + ")");
    }
  }

  static void checkNoOverflow(boolean condition, String methodName, long a, long b) {
    if (!condition) {
      throw new ArithmeticException("overflow: " + methodName + "(" + a + ", " + b + ")");
    }
  }

  private MathPreconditions() {}
}
