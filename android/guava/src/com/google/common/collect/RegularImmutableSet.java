

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * Implementation of {@link ImmutableSet} with two or more elements.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible(serializable = true, emulated = true)
@SuppressWarnings("serial") // uses writeReplace(), not default serialization
final class RegularImmutableSet<E> extends ImmutableSet<E> {
  static final RegularImmutableSet<Object> EMPTY =
      new RegularImmutableSet<>(new Object[0], 0, null, 0, 0);

  @VisibleForTesting final transient Object[] elements;
  // the same elements in hashed positions (plus nulls)
  @VisibleForTesting final transient Object[] table;
  // 'and' with an int to get a valid table index.
  private final transient int mask;
  private final transient int hashCode;
  private final transient int size;

  RegularImmutableSet(Object[] elements, int hashCode, Object[] table, int mask, int size) {
    this.elements = elements;
    this.table = table;
    this.mask = mask;
    this.hashCode = hashCode;
    this.size = size;
  }

  @Override
  public boolean contains(@NullableDecl Object target) {
    Object[] table = this.table;
    if (target == null || table == null) {
      return false;
    }
    for (int i = Hashing.smearedHash(target); ; i++) {
      i &= mask;
      Object candidate = table[i];
      if (candidate == null) {
        return false;
      } else if (candidate.equals(target)) {
        return true;
      }
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public UnmodifiableIterator<E> iterator() {
    return asList().iterator();
  }

  @Override
  Object[] internalArray() {
    return elements;
  }

  @Override
  int internalArrayStart() {
    return 0;
  }

  @Override
  int internalArrayEnd() {
    return size;
  }

  @Override
  int copyIntoArray(Object[] dst, int offset) {
    System.arraycopy(elements, 0, dst, offset, size);
    return offset + size;
  }

  @Override
  ImmutableList<E> createAsList() {
    return ImmutableList.asImmutableList(elements, size);
  }

  @Override
  boolean isPartialView() {
    return false;
  }

  @Override
  public int hashCode() {
    return hashCode;
  }

  @Override
  boolean isHashCodeFast() {
    return true;
  }
}
