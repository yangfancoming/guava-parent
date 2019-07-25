package com.google.common.collect;

import com.google.common.testing.AbstractPackageSanityTests;

/**
 * Covers basic sanity checks for the entire package.
 *
 * @author Ben Yu
 */

public class PackageSanityTests extends AbstractPackageSanityTests {
  public PackageSanityTests() {
    publicApiOnly(); // Many package-private classes are tested through the public API.
    setDefault(DiscreteDomain.class, DiscreteDomain.integers());
  }
}
