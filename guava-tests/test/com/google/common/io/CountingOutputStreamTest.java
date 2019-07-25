

package com.google.common.io;

import java.io.ByteArrayOutputStream;

/**
 * Unit tests for {@link CountingOutputStream}.
 *
 * @author Chris Nokleberg
 */
public class CountingOutputStreamTest extends IoTestCase {

  public void testCount() throws Exception {
    int written = 0;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    CountingOutputStream counter = new CountingOutputStream(out);
    assertEquals(written, out.size());
    assertEquals(written, counter.getCount());

    counter.write(0);
    written += 1;
    assertEquals(written, out.size());
    assertEquals(written, counter.getCount());

    byte[] data = new byte[10];
    counter.write(data);
    written += 10;
    assertEquals(written, out.size());
    assertEquals(written, counter.getCount());

    counter.write(data, 0, 5);
    written += 5;
    assertEquals(written, out.size());
    assertEquals(written, counter.getCount());

    counter.write(data, 2, 5);
    written += 5;
    assertEquals(written, out.size());
    assertEquals(written, counter.getCount());

    // Test that illegal arguments do not affect count
    try {
      counter.write(data, 0, data.length + 1);
      fail("expected exception");
    } catch (IndexOutOfBoundsException expected) {
    }
    assertEquals(written, out.size());
    assertEquals(written, counter.getCount());
  }
}
