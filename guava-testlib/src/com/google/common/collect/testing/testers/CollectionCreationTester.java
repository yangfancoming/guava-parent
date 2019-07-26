

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.ALLOWS_NULL_VALUES;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.testing.AbstractCollectionTester;
import com.google.common.collect.testing.Helpers;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import java.lang.reflect.Method;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests creation (typically through a constructor or static factory
 * method) of a collection. Can't be invoked directly; please see {@link
 * com.google.common.collect.testing.CollectionTestSuiteBuilder}.
 *
 * @author Chris Povirk
 */
@GwtCompatible(emulated = true)
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class CollectionCreationTester<E> extends AbstractCollectionTester<E> {
  @CollectionFeature.Require(ALLOWS_NULL_VALUES)
  @CollectionSize.Require(absent = ZERO)
  public void testCreateWithNull_supported() {
    E[] array = createArrayWithNullElement();
    collection = getSubjectGenerator().create(array);
    expectContents(array);
  }

  @CollectionFeature.Require(absent = ALLOWS_NULL_VALUES)
  @CollectionSize.Require(absent = ZERO)
  public void testCreateWithNull_unsupported() {
    E[] array = createArrayWithNullElement();

    try {
      getSubjectGenerator().create(array);
      fail("Creating a collection containing null should fail");
    } catch (NullPointerException expected) {
    }
  }

  /**
   * Returns the {@link Method} instance for {@link #testCreateWithNull_unsupported()} so that tests
   * can suppress it with {@code FeatureSpecificTestSuiteBuilder.suppressing()} until <a
   * href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=5045147">Sun bug 5045147</a> is fixed.
   */
  @GwtIncompatible // reflection
  public static Method getCreateWithNullUnsupportedMethod() {
    return Helpers.getMethod(CollectionCreationTester.class, "testCreateWithNull_unsupported");
  }
}
