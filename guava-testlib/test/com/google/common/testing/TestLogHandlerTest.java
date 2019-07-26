

package com.google.common.testing;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 * Unit test for {@link TestLogHandler}.
 *
 * @author kevinb
 */
public class TestLogHandlerTest extends TestCase {

  private TestLogHandler handler;
  private TearDownStack stack = new TearDownStack();

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    handler = new TestLogHandler();

    // You could also apply it higher up the Logger hierarchy than this
    ExampleClassUnderTest.logger.addHandler(handler);

    ExampleClassUnderTest.logger.setUseParentHandlers(false); // optional

    stack.addTearDown(
        new TearDown() {
          @Override
          public void tearDown() throws Exception {
            ExampleClassUnderTest.logger.setUseParentHandlers(true);
            ExampleClassUnderTest.logger.removeHandler(handler);
          }
        });
  }

  public void test() throws Exception {
    assertTrue(handler.getStoredLogRecords().isEmpty());
    ExampleClassUnderTest.foo();
    LogRecord record = handler.getStoredLogRecords().get(0);
    assertEquals(Level.INFO, record.getLevel());
    assertEquals("message", record.getMessage());
    assertSame(EXCEPTION, record.getThrown());
  }

  public void testConcurrentModification() throws Exception {
    // Tests for the absence of a bug where logging while iterating over the
    // stored log records causes a ConcurrentModificationException
    assertTrue(handler.getStoredLogRecords().isEmpty());
    ExampleClassUnderTest.foo();
    ExampleClassUnderTest.foo();
    for (LogRecord unused : handler.getStoredLogRecords()) {
      ExampleClassUnderTest.foo();
    }
  }

  @Override
  public final void runBare() throws Throwable {
    try {
      setUp();
      runTest();
    } finally {
      tearDown();
    }
  }

  @Override
  protected void tearDown() {
    stack.runTearDown();
  }

  static final Exception EXCEPTION = new Exception();

  static class ExampleClassUnderTest {
    static final Logger logger = Logger.getLogger(ExampleClassUnderTest.class.getName());

    static void foo() {
      logger.log(Level.INFO, "message", EXCEPTION);
    }
  }
}
