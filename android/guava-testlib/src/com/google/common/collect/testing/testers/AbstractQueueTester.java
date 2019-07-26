

package com.google.common.collect.testing.testers;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractCollectionTester;
import java.util.Queue;
import org.junit.Ignore;

/**
 * Base class for queue collection tests.
 *
 * @author Jared Levy
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class AbstractQueueTester<E> extends AbstractCollectionTester<E> {
  protected final Queue<E> getQueue() {
    return (Queue<E>) collection;
  }
}
