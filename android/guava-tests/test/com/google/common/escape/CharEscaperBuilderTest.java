package com.google.common.escape;

import junit.framework.TestCase;

public class CharEscaperBuilderTest extends TestCase {

  public void testAddEscapes() {
    char[] cs = {'a', 'b', 'c'};
    CharEscaperBuilder builder = new CharEscaperBuilder().addEscapes(cs, "Z");
    Escaper escaper = builder.toEscaper();
    assertEquals("ZZZdef", escaper.escape("abcdef"));
  }
}
