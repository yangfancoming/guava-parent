package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.singletonList;

import java.util.List;

/**
 * GWT emulated version of {@link SingletonImmutableList}.
 *
 * @author Hayward Chan
 */
final class SingletonImmutableList<E> extends ForwardingImmutableList<E> {

  final transient List<E> delegate;
  // This reference is used both by the custom field serializer, and by the
  // GWT compiler to infer the elements of the lists that needs to be
  // serialized.
  E element;

  SingletonImmutableList(E element) {
    this.delegate = singletonList(checkNotNull(element));
    this.element = element;
  }

  @Override
  List<E> delegateList() {
    return delegate;
  }
}
