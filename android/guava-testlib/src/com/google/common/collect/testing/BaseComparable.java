

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

/**
 * Simple base class to verify that we handle generics correctly.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
public class BaseComparable implements Comparable<BaseComparable>, Serializable {
  private final String s;

  public BaseComparable(String s) {
    this.s = s;
  }

  @Override
  public int hashCode() { // delegate to 's'
    return s.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    } else if (other instanceof BaseComparable) {
      return s.equals(((BaseComparable) other).s);
    } else {
      return false;
    }
  }

  @Override
  public int compareTo(BaseComparable o) {
    return s.compareTo(o.s);
  }

  private static final long serialVersionUID = 0;
}
