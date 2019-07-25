package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Weak reference with a {@code finalizeReferent()} method which a background thread invokes after
 * the garbage collector reclaims the referent. This is a simpler alternative to using a {@link
 * ReferenceQueue}.
 *
 * @author Bob Lee
 * @since 2.0
 */
@GwtIncompatible
public abstract class FinalizableWeakReference<T> extends WeakReference<T>
    implements FinalizableReference {
  /**
   * Constructs a new finalizable weak reference.
   *
   * @param referent to weakly reference
   * @param queue that should finalize the referent
   */
  protected FinalizableWeakReference(T referent, FinalizableReferenceQueue queue) {
    super(referent, queue.queue);
    queue.cleanUp();
  }
}
