

package com.google.common.cache;

/**
 * Utility {@link Weigher} implementations intended for use in testing.
 *
 * @author Charles Fry
 */
public class TestingWeighers {

  /** Returns a {@link Weigher} that returns the given {@code constant} for every request. */
  static Weigher<Object, Object> constantWeigher(int constant) {
    return new ConstantWeigher(constant);
  }

  /** Returns a {@link Weigher} that uses the integer key as the weight. */
  static Weigher<Integer, Object> intKeyWeigher() {
    return new IntKeyWeigher();
  }

  /** Returns a {@link Weigher} that uses the integer value as the weight. */
  static Weigher<Object, Integer> intValueWeigher() {
    return new IntValueWeigher();
  }

  static final class ConstantWeigher implements Weigher<Object, Object> {
    private final int constant;

    ConstantWeigher(int constant) {
      this.constant = constant;
    }

    @Override
    public int weigh(Object key, Object value) {
      return constant;
    }
  }

  static final class IntKeyWeigher implements Weigher<Integer, Object> {
    @Override
    public int weigh(Integer key, Object value) {
      return key;
    }
  }

  static final class IntValueWeigher implements Weigher<Object, Integer> {
    @Override
    public int weigh(Object key, Integer value) {
      return value;
    }
  }
}
