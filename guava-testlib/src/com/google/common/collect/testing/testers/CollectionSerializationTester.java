package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.SERIALIZABLE;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractCollectionTester;
import com.google.common.collect.testing.Helpers;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.testing.SerializableTester;
import org.junit.Ignore;

/**
 * Basic reserialization test for collections.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class CollectionSerializationTester<E> extends AbstractCollectionTester<E> {
  @CollectionFeature.Require(SERIALIZABLE)
  public void testReserialize() {
    // For a bare Collection, the most we can guarantee is that the elements are preserved.
    Helpers.assertEqualIgnoringOrder(
        actualContents(), SerializableTester.reserialize(actualContents()));
  }
}
