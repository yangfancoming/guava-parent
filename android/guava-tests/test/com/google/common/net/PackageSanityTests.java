package com.google.common.net;

import com.google.common.testing.AbstractPackageSanityTests;

/**
 * Basic sanity tests for the entire package.
 *
 * @author Ben Yu
 */

public class PackageSanityTests extends AbstractPackageSanityTests {
  public PackageSanityTests() {
    setDefault(InternetDomainName.class, InternetDomainName.from("google.com"));
  }
}
