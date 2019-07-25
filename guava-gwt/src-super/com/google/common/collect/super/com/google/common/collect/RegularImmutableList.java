package com.google.common.collect;

import static java.util.Collections.unmodifiableList;

import java.util.List;

/**
 * GWT emulated version of {@link RegularImmutableList}.
 *
 * @author Hayward Chan
 */
class RegularImmutableList<E> extends ForwardingImmutableList<E> {
  private final List<E> delegate;
  E forSerialization;

  RegularImmutableList(List<E> delegate) {
    // TODO(cpovirk): avoid redundant unmodifiableList wrapping
    this.delegate = unmodifiableList(delegate);
  }

  @Override
  List<E> delegateList() {
    return delegate;
  }
}
