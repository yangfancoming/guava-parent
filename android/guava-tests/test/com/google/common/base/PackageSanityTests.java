package com.google.common.base;

import com.google.common.testing.AbstractPackageSanityTests;

/** Basic sanity tests for classes in {@code common.base}. */

public class PackageSanityTests extends AbstractPackageSanityTests {
  public PackageSanityTests() {
    // package private classes like FunctionalEquivalence are tested through the public API.
    publicApiOnly();
  }
}
