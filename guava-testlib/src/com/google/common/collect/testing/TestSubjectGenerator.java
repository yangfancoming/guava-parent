

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;

/**
 * To be implemented by test generators that can produce test subjects without requiring any
 * parameters.
 *
 * @param <T> the type created by this generator.
 * @author George van den Driessche
 */
@GwtCompatible
public interface TestSubjectGenerator<T> {
  T createTestSubject();
}
