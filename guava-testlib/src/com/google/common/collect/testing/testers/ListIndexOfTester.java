

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.REJECTS_DUPLICATES_AT_CREATION;
import static com.google.common.collect.testing.features.CollectionSize.ONE;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests {@code indexOf()} operations on a list. Can't be invoked
 * directly; please see {@link com.google.common.collect.testing.ListTestSuiteBuilder}.
 *
 * @author Chris Povirk
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class ListIndexOfTester<E> extends AbstractListIndexOfTester<E> {
  @Override
  protected int find(Object o) {
    return getList().indexOf(o);
  }

  @Override
  protected String getMethodName() {
    return "indexOf";
  }

  @CollectionFeature.Require(absent = REJECTS_DUPLICATES_AT_CREATION)
  @CollectionSize.Require(absent = {ZERO, ONE})
  public void testIndexOf_duplicate() {
    E[] array = createSamplesArray();
    array[getNumElements() / 2] = e0();
    collection = getSubjectGenerator().create(array);
    assertEquals(
        "indexOf(duplicate) should return index of first occurrence", 0, getList().indexOf(e0()));
  }
}
