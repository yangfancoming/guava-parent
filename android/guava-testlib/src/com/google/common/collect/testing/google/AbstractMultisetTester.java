

package com.google.common.collect.testing.google;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.testing.AbstractCollectionTester;
import org.junit.Ignore;

/**
 * Base class for multiset collection tests.
 *
 * @author Jared Levy
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class AbstractMultisetTester<E> extends AbstractCollectionTester<E> {
  protected final Multiset<E> getMultiset() {
    return (Multiset<E>) collection;
  }

  protected void initThreeCopies() {
    collection = getSubjectGenerator().create(e0(), e0(), e0());
  }
}
