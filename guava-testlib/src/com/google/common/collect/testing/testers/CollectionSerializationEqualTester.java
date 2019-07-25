package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.SERIALIZABLE;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractCollectionTester;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.testing.SerializableTester;
import org.junit.Ignore;

/**
 * Basic reserialization test for collection types that must preserve {@code equals()} behavior when
 * reserialized. (Sets and Lists, but not bare Collections.)
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class CollectionSerializationEqualTester<E> extends AbstractCollectionTester<E> {
  @CollectionFeature.Require(SERIALIZABLE)
  public void testReserialize() {
    assertEquals(SerializableTester.reserialize(actualContents()), actualContents());
  }
}
