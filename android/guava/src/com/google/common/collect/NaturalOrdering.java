

package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

/** An ordering that uses the natural order of the values. */
@GwtCompatible(serializable = true)
@SuppressWarnings({"unchecked", "rawtypes"}) // TODO(kevinb): the right way to explain this??
final class NaturalOrdering extends Ordering<Comparable> implements Serializable {
  static final NaturalOrdering INSTANCE = new NaturalOrdering();

  @MonotonicNonNullDecl private transient Ordering<Comparable> nullsFirst;
  @MonotonicNonNullDecl private transient Ordering<Comparable> nullsLast;

  @Override
  public int compare(Comparable left, Comparable right) {
    checkNotNull(left); // for GWT
    checkNotNull(right);
    return left.compareTo(right);
  }

  @Override
  public <S extends Comparable> Ordering<S> nullsFirst() {
    Ordering<Comparable> result = nullsFirst;
    if (result == null) {
      result = nullsFirst = super.nullsFirst();
    }
    return (Ordering<S>) result;
  }

  @Override
  public <S extends Comparable> Ordering<S> nullsLast() {
    Ordering<Comparable> result = nullsLast;
    if (result == null) {
      result = nullsLast = super.nullsLast();
    }
    return (Ordering<S>) result;
  }

  @Override
  public <S extends Comparable> Ordering<S> reverse() {
    return (Ordering<S>) ReverseNaturalOrdering.INSTANCE;
  }

  // preserving singleton-ness gives equals()/hashCode() for free
  private Object readResolve() {
    return INSTANCE;
  }

  @Override
  public String toString() {
    return "Ordering.natural()";
  }

  private NaturalOrdering() {}

  private static final long serialVersionUID = 0;
}
