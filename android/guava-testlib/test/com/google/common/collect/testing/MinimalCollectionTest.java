package com.google.common.collect.testing;

import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import java.util.Collection;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Unit test for {@link MinimalCollection}.
 *
 * @author Kevin Bourrillion
 */
public class MinimalCollectionTest extends TestCase {
  public static Test suite() {
    return CollectionTestSuiteBuilder.using(
            new TestStringCollectionGenerator() {
              @Override
              public Collection<String> create(String[] elements) {
                // TODO: MinimalCollection should perhaps throw
                for (Object element : elements) {
                  if (element == null) {
                    throw new NullPointerException();
                  }
                }
                return MinimalCollection.of(elements);
              }
            })
        .named("MinimalCollection")
        .withFeatures(CollectionFeature.NONE, CollectionSize.ANY)
        .createTestSuite();
  }
}
