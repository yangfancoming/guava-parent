package com.google.common.testing;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple utility for when you want to create a {@link TearDown} that may throw an exception but
 * should not fail a test when it does. (The behavior of a {@code TearDown} that throws an exception
 * varies; see its documentation for details.) Use it just like a {@code TearDown}, except override
 * {@link #sloppyTearDown()} instead.
 *
 * @author Luiz-Otavio Zorzella
 * @since 10.0
 */
@Beta
@GwtCompatible
public abstract class SloppyTearDown implements TearDown {
  private static final Logger logger = Logger.getLogger(SloppyTearDown.class.getName());

  @Override
  public final void tearDown() {
    try {
      sloppyTearDown();
    } catch (Throwable t) {
      logger.log(Level.INFO, "exception thrown during tearDown: " + t.getMessage(), t);
    }
  }

  public abstract void sloppyTearDown() throws Exception;
}
