package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;

/**
 * An unhashable object to be used in testing as values in our collections.
 *
 * @author Regina O'Dell
 */
@GwtCompatible
public class UnhashableObject implements Comparable<UnhashableObject> {
  private final int value;

  public UnhashableObject(int value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof UnhashableObject) {
      UnhashableObject that = (UnhashableObject) object;
      return this.value == that.value;
    }
    return false;
  }

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException();
  }

  // needed because otherwise Object.toString() calls hashCode()
  @Override
  public String toString() {
    return "DontHashMe" + value;
  }

  @Override
  public int compareTo(UnhashableObject o) {
    return (this.value < o.value) ? -1 : (this.value > o.value) ? 1 : 0;
  }
}
