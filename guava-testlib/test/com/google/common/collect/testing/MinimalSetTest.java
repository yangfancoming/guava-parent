package com.google.common.collect.testing;

import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import java.util.Set;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Unit test for {@link MinimalSet}.
 *
 * @author Regina O'Dell
 */
public class MinimalSetTest extends TestCase {
  public static Test suite() {
    return SetTestSuiteBuilder.using(
            new TestStringSetGenerator() {
              @Override
              protected Set<String> create(String[] elements) {
                return MinimalSet.of(elements);
              }
            })
        .named("MinimalSet")
        .withFeatures(
            CollectionFeature.ALLOWS_NULL_VALUES, CollectionFeature.NONE, CollectionSize.ANY)
        .createTestSuite();
  }
}
