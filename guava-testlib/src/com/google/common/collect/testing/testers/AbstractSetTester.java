

package com.google.common.collect.testing.testers;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractCollectionTester;
import java.util.Set;
import org.junit.Ignore;

/** @author George van den Driessche */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class AbstractSetTester<E> extends AbstractCollectionTester<E> {
  /*
   * Previously we had a field named set that was initialized to the value of
   * collection in setUp(), but that caused problems when a tester changed the
   * value of set or collection but not both.
   */
  protected final Set<E> getSet() {
    return (Set<E>) collection;
  }
}
