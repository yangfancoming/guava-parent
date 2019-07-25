

package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * Provides equivalent behavior to {@link String#intern} for other immutable types. Common
 * implementations are available from the {@link Interners} class.
 *
 * @author Kevin Bourrillion
 * @since 3.0
 */
@Beta
@GwtIncompatible
public interface Interner<E> {
  /**
   * Chooses and returns the representative instance for any of a collection of instances that are
   * equal to each other. If two {@linkplain Object#equals equal} inputs are given to this method,
   * both calls will return the same instance. That is, {@code intern(a).equals(a)} always holds,
   * and {@code intern(a) == intern(b)} if and only if {@code a.equals(b)}. Note that {@code
   * intern(a)} is permitted to return one instance now and a different instance later if the
   * original interned instance was garbage-collected.
   *
   * <p><b>Warning:</b> do not use with mutable objects.
   *
   * @throws NullPointerException if {@code sample} is null
   */
  @CanIgnoreReturnValue // TODO(cpovirk): Consider removing this?
  E intern(E sample);
}
