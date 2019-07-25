

package com.google.common.cache;

/**
 * No-op null-pointer test for {@link LongAdder} to override the {@link PackageSanityTests} version,
 * which checks package-private methods that we don't want to have to annotate as {@code Nullable}
 * because we don't want diffs from jsr166e.
 */
public class LongAdderTest {
  public void testNulls() {}
}
