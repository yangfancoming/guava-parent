

package com.google.common.testing;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Methods factored out so that they can be emulated differently in GWT.
 *
 * @author Chris Povirk
 */
final class Platform {
  /** Serializes and deserializes the specified object (a no-op under GWT). */
  @SuppressWarnings("unchecked")
  static <T> T reserialize(T object) {
    return checkNotNull(object);
  }

  private Platform() {}
}
