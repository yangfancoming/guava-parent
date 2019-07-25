

package com.google.common.base;

import static com.google.common.base.Verify.verify;
import static com.google.common.base.Verify.verifyNotNull;
import static com.google.common.truth.Truth.assertThat;

import com.google.common.annotations.GwtCompatible;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

/** Unit test for {@link com.google.common.base.Verify}. */
@GwtCompatible
public class VerifyTest extends TestCase {
  public void testVerify_simple_success() {
    verify(true);
  }

  public void testVerify_simple_failure() {
    try {
      verify(false);
      fail();
    } catch (VerifyException expected) {
    }
  }

  public void testVerify_simpleMessage_success() {
    verify(true, "message");
  }

  public void testVerify_simpleMessage_failure() {
    try {
      verify(false, "message");
      fail();
    } catch (VerifyException expected) {
      assertThat(expected).hasMessageThat().isEqualTo("message");
    }
  }

  public void testVerify_complexMessage_success() {
    verify(true, "%s", IGNORE_ME);
  }

  public void testVerify_complexMessage_failure() {
    try {
      verify(false, FORMAT, 5);
      fail();
    } catch (VerifyException expected) {
      checkMessage(expected);
    }
  }

  private static final String NON_NULL_STRING = "foo";

  public void testVerifyNotNull_simple_success() {
    String result = verifyNotNull(NON_NULL_STRING);
    assertSame(NON_NULL_STRING, result);
  }

  public void testVerifyNotNull_simple_failure() {
    try {
      verifyNotNull(null);
      fail();
    } catch (VerifyException expected) {
    }
  }

  public void testVerifyNotNull_complexMessage_success() {
    String result = verifyNotNull(NON_NULL_STRING, "%s", IGNORE_ME);
    assertSame(NON_NULL_STRING, result);
  }

  public void testVerifyNotNull_simpleMessage_failure() {
    try {
      verifyNotNull(null, FORMAT, 5);
      fail();
    } catch (VerifyException expected) {
      checkMessage(expected);
    }
  }

  private static final Object IGNORE_ME =
      new Object() {
        @Override
        public String toString() {
          throw new AssertionFailedError();
        }
      };

  private static final String FORMAT = "I ate %s pies.";

  private static void checkMessage(Exception e) {
    assertThat(e).hasMessageThat().isEqualTo("I ate 5 pies.");
  }
}
