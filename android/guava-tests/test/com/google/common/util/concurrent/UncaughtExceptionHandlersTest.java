

package com.google.common.util.concurrent;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.google.common.util.concurrent.UncaughtExceptionHandlers.Exiter;
import junit.framework.TestCase;

/** @author Gregory Kick */

public class UncaughtExceptionHandlersTest extends TestCase {

  private Runtime runtimeMock;

  @Override
  protected void setUp() {
    runtimeMock = mock(Runtime.class);
  }

  public void testExiter() {
    new Exiter(runtimeMock).uncaughtException(new Thread(), new Exception());
    verify(runtimeMock).exit(1);
  }
}
