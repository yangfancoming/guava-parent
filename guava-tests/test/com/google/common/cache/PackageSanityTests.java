package com.google.common.cache;

import com.google.common.testing.AbstractPackageSanityTests;

/**
 * Basic sanity tests for the entire package.
 *
 * @author Ben Yu
 */

public class PackageSanityTests extends AbstractPackageSanityTests {
  public PackageSanityTests() {
    setDefault(
        CacheLoader.class,
        new CacheLoader<Object, Object>() {
          @Override
          public Object load(Object key) {
            return key;
          }
        });
    setDefault(LocalCache.class, new LocalCache<Object, Object>(CacheBuilder.newBuilder(), null));
    setDefault(CacheBuilder.class, CacheBuilder.newBuilder());
  }
}
