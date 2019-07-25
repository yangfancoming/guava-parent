package java.lang;

/**
 * Minimal emulation of {@link java.lang.InterruptedException}, that should only be used in method
 * signatures. New GWT code should not reference this class at all. It is here only to ease the
 * GWTification of common code.
 *
 * @author Tom O'Neill
 */
public class InterruptedException extends Exception {
  public InterruptedException() {}

  public InterruptedException(String message) {
    super(message);
  }
}
