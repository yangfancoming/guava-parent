package com.google.common.collect;

import java.util.Set;

/**
 * GWT emulation of {@link ImmutableEnumSet}. The type parameter is not bounded by {@code Enum<E>}
 * to avoid code-size bloat.
 *
 * @author Hayward Chan
 */
final class ImmutableEnumSet<E> extends ForwardingImmutableSet<E> {
  static <E> ImmutableSet<E> asImmutable(Set<E> delegate) {
    switch (delegate.size()) {
      case 0:
        return ImmutableSet.of();
      case 1:
        return ImmutableSet.of(Iterables.getOnlyElement(delegate));
      default:
        return new ImmutableEnumSet<E>(delegate);
    }
  }

  public ImmutableEnumSet(Set<E> delegate) {
    super(delegate);
  }
}
