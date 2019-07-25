package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * Phantom reference with a {@code finalizeReferent()} method which a background thread invokes
 * after the garbage collector reclaims the referent. This is a simpler alternative to using a
 * {@link ReferenceQueue}.
 *
 * <p>Unlike a normal phantom reference, this reference will be cleared automatically.
 *
 * @author Bob Lee
 * @since 2.0
 */
@GwtIncompatible
public abstract class FinalizablePhantomReference<T> extends PhantomReference<T>
    implements FinalizableReference {
  /**
   * Constructs a new finalizable phantom reference.
   *
   * @param referent to phantom reference
   * @param queue that should finalize the referent
   */
  protected FinalizablePhantomReference(T referent, FinalizableReferenceQueue queue) {
    super(referent, queue.queue);
    queue.cleanUp();
  }
}
