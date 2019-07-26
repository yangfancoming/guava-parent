

package com.google.common.testing;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

/**
 * Any object which can accept registrations of {@link TearDown} instances.
 *
 * @author Kevin Bourrillion
 * @since 10.0
 */
@Beta
@GwtCompatible
public interface TearDownAccepter {
  /**
   * Registers a TearDown implementor which will be run after the test proper.
   *
   * <p>In JUnit4 language, that means as an {@code @After}.
   *
   * <p>In JUnit3 language, that means during the {@link junit.framework.TestCase#tearDown()} step.
   */
  void addTearDown(TearDown tearDown);
}
