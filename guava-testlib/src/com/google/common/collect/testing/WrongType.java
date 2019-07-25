

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;

/**
 * A type which will never be used as the element type of any collection in our tests, and so can be
 * used to test how a Collection behaves when given input of the wrong type.
 */
@GwtCompatible
public enum WrongType {
  VALUE
}
