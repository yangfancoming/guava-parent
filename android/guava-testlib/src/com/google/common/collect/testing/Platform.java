

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.Locale;

/**
 * Methods factored out so that they can be emulated differently in GWT.
 *
 * <p>This class is emulated in GWT.
 *
 * @author Hayward Chan
 */
@GwtCompatible
final class Platform {
  static <T> T[] clone(T[] array) {
    return array.clone();
  }

  // Class.cast is not supported in GWT.  This method is a no-op in GWT.
  static void checkCast(Class<?> clazz, Object obj) {
    clazz.cast(obj);
  }

  static String format(String template, Object... args) {
    return String.format(Locale.ROOT, template, args);
  }

  private Platform() {}
}
