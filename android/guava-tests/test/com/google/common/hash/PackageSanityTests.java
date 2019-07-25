package com.google.common.hash;

import com.google.common.hash.BloomFilterStrategies.LockFreeBitArray;
import com.google.common.testing.AbstractPackageSanityTests;

/**
 * Basic sanity tests for the entire package.
 *
 * @author Ben Yu
 */

public class PackageSanityTests extends AbstractPackageSanityTests {
  public PackageSanityTests() {
    setDefault(LockFreeBitArray.class, new LockFreeBitArray(1));
    setDefault(HashCode.class, HashCode.fromInt(1));
    setDefault(String.class, "MD5");
    setDefault(int.class, 32);
  }
}
