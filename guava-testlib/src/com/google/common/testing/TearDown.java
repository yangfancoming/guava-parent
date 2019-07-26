

package com.google.common.testing;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

/**
 * An object that can perform a {@link #tearDown} operation.
 *
 * @author Kevin Bourrillion
 * @since 10.0
 */
@Beta
@FunctionalInterface
@GwtCompatible
public interface TearDown {
  /**
   * Performs a <b>single</b> tear-down operation. See test-libraries-for-java's {@code
   * com.google.common.testing.junit3.TearDownTestCase} and {@code
   * com.google.common.testing.junit4.TearDownTestCase} for example.
   *
   * <p>A failing {@link TearDown} may or may not fail a tl4j test, depending on the version of
   * JUnit test case you are running under. To avoid failing in the face of an exception regardless
   * of JUnit version, implement a {@link SloppyTearDown} instead.
   *
   * <p>tl4j details: For backwards compatibility, {@code junit3.TearDownTestCase} currently does
   * not fail a test when an exception is thrown from one of its {@link TearDown} instances, but
   * this is subject to change. Also, {@code junit4.TearDownTestCase} will.
   *
   * @throws Exception for any reason. {@code TearDownTestCase} ensures that any exception thrown
   *     will not interfere with other TearDown operations.
   */
  void tearDown() throws Exception;
}
