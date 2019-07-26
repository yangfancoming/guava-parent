

package com.google.common.collect.testing;

import java.util.Arrays;

/**
 * Minimal GWT emulation of {@code com.google.common.collect.testing.Platform}.
 *
 * <p><strong>This .java file should never be consumed by javac.</strong>
 *
 * @author Hayward Chan
 */
final class Platform {
  // Class.cast is not supported in GWT.
  static void checkCast(Class<?> clazz, Object obj) {}

  static <T> T[] clone(T[] array) {
    return (T[]) Arrays.copyOfRange(array, 0, array.length);
  }

  // TODO: Consolidate different copies in one single place.
  static String format(String template, Object... args) {
    // start substituting the arguments into the '%s' placeholders
    StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
    int templateStart = 0;
    int i = 0;
    while (i < args.length) {
      int placeholderStart = template.indexOf("%s", templateStart);
      if (placeholderStart == -1) {
        break;
      }
      builder.append(template.substring(templateStart, placeholderStart));
      builder.append(args[i++]);
      templateStart = placeholderStart + 2;
    }
    builder.append(template.substring(templateStart));

    // if we run out of placeholders, append the extra args in square braces
    if (i < args.length) {
      builder.append(" [");
      builder.append(args[i++]);
      while (i < args.length) {
        builder.append(", ");
        builder.append(args[i++]);
      }
      builder.append("]");
    }

    return builder.toString();
  }

  private Platform() {}
}
