package com.google.common.io;

/**
 * Interface for a supplier of streams that can report whether a stream was opened and whether that
 * stream was closed. Intended for use in a test where only a single stream should be opened and
 * possibly closed.
 *
 * @author Colin Decker
 */
public interface TestStreamSupplier {

  /** Returns whether or not a new stream was opened. */
  boolean wasStreamOpened();

  /** Returns whether or not an open stream was closed. */
  boolean wasStreamClosed();
}
