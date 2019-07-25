

package com.google.common.math;

import com.google.common.annotations.GwtCompatible;

/** @author Chris Povirk */
@GwtCompatible(emulated = true)
class TestPlatform {
  static boolean intsCanGoOutOfRange() {
    return true;
  }

  static boolean isAndroid() {
    return false;
  }
}
