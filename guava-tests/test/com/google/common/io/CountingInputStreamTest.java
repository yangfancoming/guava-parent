

package com.google.common.io;

import static com.google.common.truth.Truth.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Unit tests for {@link CountingInputStream}.
 *
 * @author Chris Nokleberg
 */
public class CountingInputStreamTest extends IoTestCase {
  private CountingInputStream counter;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    counter = new CountingInputStream(new ByteArrayInputStream(new byte[20]));
  }

  public void testReadSingleByte() throws IOException {
    assertEquals(0, counter.getCount());
    assertEquals(0, counter.read());
    assertEquals(1, counter.getCount());
  }

  public void testReadArray() throws IOException {
    assertEquals(10, counter.read(new byte[10]));
    assertEquals(10, counter.getCount());
  }

  public void testReadArrayRange() throws IOException {
    assertEquals(3, counter.read(new byte[10], 1, 3));
    assertEquals(3, counter.getCount());
  }

  public void testSkip() throws IOException {
    assertEquals(10, counter.skip(10));
    assertEquals(10, counter.getCount());
  }

  public void testSkipEOF() throws IOException {
    assertEquals(20, counter.skip(30));
    assertEquals(20, counter.getCount());
    assertEquals(0, counter.skip(20));
    assertEquals(20, counter.getCount());

    // Test reading a single byte while we're in the right state
    assertEquals(-1, counter.read());
    assertEquals(20, counter.getCount());
  }

  public void testReadArrayEOF() throws IOException {
    assertEquals(20, counter.read(new byte[30]));
    assertEquals(20, counter.getCount());
    assertEquals(-1, counter.read(new byte[30]));
    assertEquals(20, counter.getCount());
  }

  @SuppressWarnings("CheckReturnValue") // calling read() to skip a byte
  public void testMark() throws Exception {
    assertTrue(counter.markSupported());
    assertEquals(10, counter.read(new byte[10]));
    assertEquals(10, counter.getCount());
    counter.mark(5);
    counter.read();
    assertEquals(11, counter.getCount());
    counter.reset();
    assertEquals(10, counter.getCount());
    assertEquals(10, counter.skip(100));
    assertEquals(20, counter.getCount());
  }

  public void testMarkNotSet() {
    try {
      counter.reset();
      fail();
    } catch (IOException expected) {
      assertThat(expected).hasMessageThat().isEqualTo("Mark not set");
    }
  }

  public void testMarkNotSupported() {
    counter = new CountingInputStream(new UnmarkableInputStream());

    try {
      counter.reset();
      fail();
    } catch (IOException expected) {
      assertThat(expected).hasMessageThat().isEqualTo("Mark not supported");
    }
  }

  private static class UnmarkableInputStream extends InputStream {
    @Override
    public int read() throws IOException {
      return 0;
    }

    @Override
    public boolean markSupported() {
      return false;
    }
  }
}
