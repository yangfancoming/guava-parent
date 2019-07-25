

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.SampleElements.Colliders;
import java.util.List;

/**
 * A generator using sample elements whose hash codes all collide badly.
 *
 * @author Kevin Bourrillion
 */
@GwtCompatible
public abstract class TestCollidingSetGenerator implements TestSetGenerator<Object> {
  @Override
  public SampleElements<Object> samples() {
    return new Colliders();
  }

  @Override
  public Object[] createArray(int length) {
    return new Object[length];
  }

  /** Returns the original element list, unchanged. */
  @Override
  public List<Object> order(List<Object> insertionOrder) {
    return insertionOrder;
  }
}
