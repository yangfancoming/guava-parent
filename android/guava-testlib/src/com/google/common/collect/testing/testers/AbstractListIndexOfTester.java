

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.ALLOWS_NULL_VALUES;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.WrongType;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import org.junit.Ignore;

/**
 * Common parent class for {@link ListIndexOfTester} and {@link ListLastIndexOfTester}.
 *
 * @author Chris Povirk
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public abstract class AbstractListIndexOfTester<E> extends AbstractListTester<E> {
  /** Override to call {@code indexOf()} or {@code lastIndexOf()}. */
  protected abstract int find(Object o);

  /** Override to return "indexOf" or "lastIndexOf()" for use in failure messages. */
  protected abstract String getMethodName();

  @CollectionSize.Require(absent = ZERO)
  public void testFind_yes() {
    assertEquals(
        getMethodName() + "(firstElement) should return 0", 0, find(getOrderedElements().get(0)));
  }

  public void testFind_no() {
    assertEquals(getMethodName() + "(notPresent) should return -1", -1, find(e3()));
  }

  @CollectionFeature.Require(ALLOWS_NULL_VALUES)
  public void testFind_nullNotContainedButSupported() {
    assertEquals(getMethodName() + "(nullNotPresent) should return -1", -1, find(null));
  }

  @CollectionFeature.Require(absent = ALLOWS_NULL_VALUES)
  public void testFind_nullNotContainedAndUnsupported() {
    try {
      assertEquals(getMethodName() + "(nullNotPresent) should return -1 or throw", -1, find(null));
    } catch (NullPointerException tolerated) {
    }
  }

  @CollectionFeature.Require(ALLOWS_NULL_VALUES)
  @CollectionSize.Require(absent = ZERO)
  public void testFind_nonNullWhenNullContained() {
    initCollectionWithNullElement();
    assertEquals(getMethodName() + "(notPresent) should return -1", -1, find(e3()));
  }

  @CollectionFeature.Require(ALLOWS_NULL_VALUES)
  @CollectionSize.Require(absent = ZERO)
  public void testFind_nullContained() {
    initCollectionWithNullElement();
    assertEquals(
        getMethodName() + "(null) should return " + getNullLocation(),
        getNullLocation(),
        find(null));
  }

  public void testFind_wrongType() {
    try {
      assertEquals(
          getMethodName() + "(wrongType) should return -1 or throw", -1, find(WrongType.VALUE));
    } catch (ClassCastException tolerated) {
    }
  }
}
