

package java.util.concurrent;

/**
 * Emulation of Callable.
 *
 * @author Charles Fry
 */
public interface Callable<V> {
  V call() throws Exception;
}
