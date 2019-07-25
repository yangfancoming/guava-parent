

package com.google.common.collect.testing.testers;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractCollectionTester;
import org.junit.Ignore;

/**
 * Tests {@link java.util.Collection#equals}.
 *
 * @author George van den Driessche
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class CollectionEqualsTester<E> extends AbstractCollectionTester<E> {

  // TODO(cpovirk): Consider using EqualsTester from Guava.
  @SuppressWarnings("SelfEquals")
  public void testEquals_self() {
    assertTrue("An Object should be equal to itself.", collection.equals(collection));
  }

  public void testEquals_null() {
    // noinspection ObjectEqualsNull
    assertFalse("An object should not be equal to null.", collection.equals(null));
  }

  public void testEquals_notACollection() {
    // noinspection EqualsBetweenInconvertibleTypes
    assertFalse(
        "A Collection should never equal an object that is not a Collection.",
        collection.equals("huh?"));
  }
}
