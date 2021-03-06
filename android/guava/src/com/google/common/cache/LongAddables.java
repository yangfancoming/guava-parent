package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Supplier;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Source of {@link LongAddable} objects that deals with GWT, Unsafe, and all that.
 *
 * @author Louis Wasserman
 */
@GwtCompatible(emulated = true)
final class LongAddables {
  private static final Supplier<LongAddable> SUPPLIER;

  static {
    Supplier<LongAddable> supplier;
    try {
      new LongAdder(); // trigger static initialization of the LongAdder class, which may fail
      supplier =
          new Supplier<LongAddable>() {
            @Override
            public LongAddable get() {
              return new LongAdder();
            }
          };
    } catch (Throwable t) { // we really want to catch *everything*
      supplier =
          new Supplier<LongAddable>() {
            @Override
            public LongAddable get() {
              return new PureJavaLongAddable();
            }
          };
    }
    SUPPLIER = supplier;
  }

  public static LongAddable create() {
    return SUPPLIER.get();
  }

  private static final class PureJavaLongAddable extends AtomicLong implements LongAddable {
    @Override
    public void increment() {
      getAndIncrement();
    }

    @Override
    public void add(long x) {
      getAndAdd(x);
    }

    @Override
    public long sum() {
      return get();
    }
  }
}
