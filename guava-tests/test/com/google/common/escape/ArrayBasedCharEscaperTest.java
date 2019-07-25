package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMap;
import com.google.common.escape.testing.EscaperAsserts;
import java.io.IOException;
import junit.framework.TestCase;

/** @author David Beaumont */
@GwtCompatible
public class ArrayBasedCharEscaperTest extends TestCase {
  private static final ImmutableMap<Character, String> NO_REPLACEMENTS = ImmutableMap.of();
  private static final ImmutableMap<Character, String> SIMPLE_REPLACEMENTS =
      ImmutableMap.of(
          '\n', "<newline>",
          '\t', "<tab>",
          '&', "<and>");

  public void testSafeRange() throws IOException {
    // Basic escaping of unsafe chars (wrap them in {,}'s)
    CharEscaper wrappingEscaper =
        new ArrayBasedCharEscaper(NO_REPLACEMENTS, 'A', 'Z') {
          @Override
          protected char[] escapeUnsafe(char c) {
            return ("{" + c + "}").toCharArray();
          }
        };
    EscaperAsserts.assertBasic(wrappingEscaper);
    // '[' and '@' lie either side of [A-Z].
    assertEquals("{[}FOO{@}BAR{]}", wrappingEscaper.escape("[FOO@BAR]"));
  }

  public void testSafeRange_maxLessThanMin() throws IOException {
    // Basic escaping of unsafe chars (wrap them in {,}'s)
    CharEscaper wrappingEscaper =
        new ArrayBasedCharEscaper(NO_REPLACEMENTS, 'Z', 'A') {
          @Override
          protected char[] escapeUnsafe(char c) {
            return ("{" + c + "}").toCharArray();
          }
        };
    EscaperAsserts.assertBasic(wrappingEscaper);
    // escape everything.
    assertEquals("{[}{F}{O}{O}{]}", wrappingEscaper.escape("[FOO]"));
  }

  public void testDeleteUnsafeChars() throws IOException {
    CharEscaper deletingEscaper =
        new ArrayBasedCharEscaper(NO_REPLACEMENTS, ' ', '~') {
          private final char[] noChars = new char[0];

          @Override
          protected char[] escapeUnsafe(char c) {
            return noChars;
          }
        };
    EscaperAsserts.assertBasic(deletingEscaper);
    assertEquals(
        "Everything outside the printable ASCII range is deleted.",
        deletingEscaper.escape(
            "\tEverything\0 outside the\uD800\uDC00 "
                + "printable ASCII \uFFFFrange is \u007Fdeleted.\n"));
  }

  public void testReplacementPriority() throws IOException {
    CharEscaper replacingEscaper =
        new ArrayBasedCharEscaper(SIMPLE_REPLACEMENTS, ' ', '~') {
          private final char[] unknown = new char[] {'?'};

          @Override
          protected char[] escapeUnsafe(char c) {
            return unknown;
          }
        };
    EscaperAsserts.assertBasic(replacingEscaper);

    // Replacements are applied first regardless of whether the character is in
    // the safe range or not ('&' is a safe char while '\t' and '\n' are not).
    assertEquals(
        "<tab>Fish <and>? Chips?<newline>", replacingEscaper.escape("\tFish &\0 Chips\r\n"));
  }
}
