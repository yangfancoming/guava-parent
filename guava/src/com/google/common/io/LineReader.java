package com.google.common.io;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.io.CharStreams.createBuffer;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A class for reading lines of text. Provides the same functionality as {@link
 * java.io.BufferedReader#readLine()} but for all {@link Readable} objects, not just instances of
 * {@link Reader}.
 *
 * @author Chris Nokleberg
 * @since 1.0
 */
@Beta
@GwtIncompatible
public final class LineReader {
  private final Readable readable;
  private final @Nullable Reader reader;
  private final CharBuffer cbuf = createBuffer();
  private final char[] buf = cbuf.array();

  private final Queue<String> lines = new LinkedList<>();
  private final LineBuffer lineBuf =
      new LineBuffer() {
        @Override
        protected void handleLine(String line, String end) {
          lines.add(line);
        }
      };

  /** Creates a new instance that will read lines from the given {@code Readable} object. */
  public LineReader(Readable readable) {
    this.readable = checkNotNull(readable);
    this.reader = (readable instanceof Reader) ? (Reader) readable : null;
  }

  /**
   * Reads a line of text. A line is considered to be terminated by any one of a line feed ({@code
   * '\n'}), a carriage return ({@code '\r'}), or a carriage return followed immediately by a
   * linefeed ({@code "\r\n"}).
   *
   * @return a {@code String} containing the contents of the line, not including any
   *     line-termination characters, or {@code null} if the end of the stream has been reached.
   * @throws IOException if an I/O error occurs
   */
  @CanIgnoreReturnValue // to skip a line
  public String readLine() throws IOException {
    while (lines.peek() == null) {
      cbuf.clear();
      // The default implementation of Reader#read(CharBuffer) allocates a
      // temporary char[], so we call Reader#read(char[], int, int) instead.
      int read = (reader != null) ? reader.read(buf, 0, buf.length) : readable.read(cbuf);
      if (read == -1) {
        lineBuf.finish();
        break;
      }
      lineBuf.add(buf, 0, read);
    }
    return lines.poll();
  }
}