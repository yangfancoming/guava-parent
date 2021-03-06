package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.testing.SetTestSuiteBuilder;
import com.google.common.collect.testing.TestStringSetGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.Feature;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for CompactHashSet.
 *
 * @author Dimitris Andreou
 */
@GwtIncompatible // java.util.Arrays#copyOf(Object[], int), java.lang.reflect.Array
public class CompactHashSetTest extends TestCase {
  public static Test suite() {
    List<Feature<?>> allFeatures =
        Arrays.<Feature<?>>asList(
            CollectionSize.ANY,
            CollectionFeature.ALLOWS_NULL_VALUES,
            CollectionFeature.FAILS_FAST_ON_CONCURRENT_MODIFICATION,
            CollectionFeature.GENERAL_PURPOSE,
            CollectionFeature.REMOVE_OPERATIONS,
            CollectionFeature.SERIALIZABLE,
            CollectionFeature.SUPPORTS_ADD,
            CollectionFeature.SUPPORTS_REMOVE);

    TestSuite suite = new TestSuite();
    suite.addTestSuite(CompactHashSetTest.class);
    suite.addTest(
        SetTestSuiteBuilder.using(
                new TestStringSetGenerator() {
                  @Override
                  protected Set<String> create(String[] elements) {
                    return CompactHashSet.create(Arrays.asList(elements));
                  }
                })
            .named("CompactHashSet")
            .withFeatures(allFeatures)
            .createTestSuite());
    suite.addTest(
        SetTestSuiteBuilder.using(
                new TestStringSetGenerator() {
                  @Override
                  protected Set<String> create(String[] elements) {
                    CompactHashSet set = CompactHashSet.create(Arrays.asList(elements));
                    for (int i = 0; i < 100; i++) {
                      set.add(i);
                    }
                    for (int i = 0; i < 100; i++) {
                      set.remove(i);
                    }
                    set.trimToSize();
                    return set;
                  }
                })
            .named("CompactHashSet#TrimToSize")
            .withFeatures(allFeatures)
            .createTestSuite());
    return suite;
  }

  public void testDummyMethod() {
    // Just make sure the test runner doesn't complain about no test methods.
  }
}
