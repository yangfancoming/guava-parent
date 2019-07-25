

package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.SUPPORTS_ADD;
import static com.google.common.collect.testing.features.CollectionSize.ZERO;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.MinimalCollection;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import org.junit.Ignore;

/**
 * A generic JUnit test which tests addAll operations on a set. Can't be invoked directly; please
 * see {@link com.google.common.collect.testing.SetTestSuiteBuilder}.
 *
 * @author Kevin Bourrillion
 */
@SuppressWarnings("unchecked") // too many "unchecked generic array creations"
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class SetAddAllTester<E> extends AbstractSetTester<E> {
  @CollectionFeature.Require(SUPPORTS_ADD)
  @CollectionSize.Require(absent = ZERO)
  public void testAddAll_supportedSomePresent() {
    assertTrue(
        "add(somePresent) should return true", getSet().addAll(MinimalCollection.of(e3(), e0())));
    expectAdded(e3());
  }

  @CollectionFeature.Require(SUPPORTS_ADD)
  public void testAddAll_withDuplicates() {
    MinimalCollection<E> elementsToAdd = MinimalCollection.of(e3(), e4(), e3(), e4());
    assertTrue("add(hasDuplicates) should return true", getSet().addAll(elementsToAdd));
    expectAdded(e3(), e4());
  }

  @CollectionFeature.Require(SUPPORTS_ADD)
  @CollectionSize.Require(absent = ZERO)
  public void testAddAll_supportedAllPresent() {
    assertFalse("add(allPresent) should return false", getSet().addAll(MinimalCollection.of(e0())));
    expectUnchanged();
  }
}
