package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * GWT emulation of {@link SingletonImmutableSet}.
 *
 * @author Hayward Chan
 */
final class SingletonImmutableSet<E> extends ImmutableSet<E> {

  // This reference is used both by the custom field serializer, and by the
  // GWT compiler to infer the elements of the lists that needs to be
  // serialized.
  //
  // Although this reference is non-final, it doesn't change after set creation.
  E element;

  SingletonImmutableSet(E element) {
    this.element = checkNotNull(element);
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public UnmodifiableIterator<E> iterator() {
    return Iterators.singletonIterator(element);
  }

  @Override
  public boolean contains(Object object) {
    return element.equals(object);
  }
}
