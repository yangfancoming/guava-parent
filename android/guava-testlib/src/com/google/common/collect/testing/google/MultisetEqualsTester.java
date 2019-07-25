

package com.google.common.collect.testing.google;

import static com.google.common.collect.testing.features.CollectionFeature.ALLOWS_NULL_VALUES;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.testing.EqualsTester;
import org.junit.Ignore;

/**
 * Tests for {@code Multiset.equals} and {@code Multiset.hashCode}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MultisetEqualsTester<E> extends AbstractMultisetTester<E> {
  public void testEqualsSameContents() {
    new EqualsTester()
        .addEqualityGroup(
            getMultiset(), getSubjectGenerator().create(getSampleElements().toArray()))
        .testEquals();
  }

  @CollectionSize.Require(absent = ZERO)
  public void testNotEqualsEmpty() {
    new EqualsTester()
        .addEqualityGroup(getMultiset())
        .addEqualityGroup(getSubjectGenerator().create())
        .testEquals();
  }

  public void testHashCodeMatchesEntrySet() {
    assertEquals(getMultiset().entrySet().hashCode(), getMultiset().hashCode());
  }

  @CollectionSize.Require(absent = ZERO)
  @CollectionFeature.Require(ALLOWS_NULL_VALUES)
  public void testEqualsMultisetWithNullValue() {
    new EqualsTester()
        .addEqualityGroup(getMultiset())
        .addEqualityGroup(
            getSubjectGenerator().create(createArrayWithNullElement()),
            getSubjectGenerator().create(createArrayWithNullElement()))
        .testEquals();
  }
}
