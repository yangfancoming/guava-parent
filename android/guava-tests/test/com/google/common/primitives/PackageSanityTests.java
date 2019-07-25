package com.google.common.primitives;

import com.google.common.testing.AbstractPackageSanityTests;

/**
 * Tests basic sanity for each class in the package.
 *
 * @author Ben Yu
 */

public class PackageSanityTests extends AbstractPackageSanityTests {
  public PackageSanityTests() {
    setDefault(String.class, "string");
  }
}
