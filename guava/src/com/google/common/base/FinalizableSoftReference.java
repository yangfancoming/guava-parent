package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * Soft reference with a {@code finalizeReferent()} method which a background thread invokes after
 * the garbage collector reclaims the referent. This is a simpler alternative to using a {@link
 * ReferenceQueue}.
 *
 * @author Bob Lee
 * @since 2.0
 */
@GwtIncompatible
public abstract class FinalizableSoftReference<T> extends SoftReference<T>
    implements FinalizableReference {
  /**
   * Constructs a new finalizable soft reference.
   *
   * @param referent to softly reference
   * @param queue that should finalize the referent
   */
  protected FinalizableSoftReference(T referent, FinalizableReferenceQueue queue) {
    super(referent, queue.queue);
    queue.cleanUp();
  }
}
