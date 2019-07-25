

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;

/**
 * Simple derived class to verify that we handle generics correctly.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
public class DerivedComparable extends BaseComparable {
  public DerivedComparable(String s) {
    super(s);
  }

  private static final long serialVersionUID = 0;
}
