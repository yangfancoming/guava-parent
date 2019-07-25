package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.SortedSet;

/**
 * Superinterface of {@link SortedMultiset} to introduce a bridge method for {@code elementSet()},
 * to ensure binary compatibility with older Guava versions that specified {@code elementSet()} to
 * return {@code SortedSet}.
 *
 * @author Louis Wasserman
 */
@GwtIncompatible
interface SortedMultisetBridge<E> extends Multiset<E> {
  @Override
  SortedSet<E> elementSet();
}
