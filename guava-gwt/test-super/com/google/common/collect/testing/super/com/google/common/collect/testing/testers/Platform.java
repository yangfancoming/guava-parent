package com.google.common.collect.testing.testers;

import com.google.common.annotations.GwtCompatible;
import com.google.gwt.core.client.GWT;

/**
 * The emulation source used in GWT.
 *
 * @author Hayward Chan
 */
@GwtCompatible(emulated = true)
final class Platform {

  // Use fewer steps in the ListIteratorTester in ListListIteratorTester because it's slow in prod
  // mode.
  static int listListIteratorTesterNumIterations() {
    // TODO(hhchan): It's 4 in java.  Figure out why even 3 is too slow in prod mode.
    return GWT.isProdMode() ? 2 : 4;
  }

  // Use fewer steps in the IteratorTester in CollectionIteratorTester because it's slow in prod
  // mode..
  static int collectionIteratorTesterNumIterations() {
    return GWT.isProdMode() ? 3 : 5;
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
