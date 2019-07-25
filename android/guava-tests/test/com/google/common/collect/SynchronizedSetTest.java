

package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.testing.SetTestSuiteBuilder;
import com.google.common.collect.testing.TestStringSetGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import junit.framework.Test;
import junit.framework.TestCase;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * Tests for {@code Synchronized#set}.
 *
 * @author Mike Bostock
 */
public class SynchronizedSetTest extends TestCase {

  public static final Object MUTEX = new Integer(1); // something Serializable

  public static Test suite() {
    return SetTestSuiteBuilder.using(
            new TestStringSetGenerator() {
              @Override
              protected Set<String> create(String[] elements) {
                TestSet<String> inner = new TestSet<>(new HashSet<String>(), MUTEX);
                Set<String> outer = Synchronized.set(inner, inner.mutex);
                Collections.addAll(outer, elements);
                return outer;
              }
            })
        .named("Synchronized.set")
        .withFeatures(
            CollectionFeature.GENERAL_PURPOSE,
            CollectionFeature.ALLOWS_NULL_VALUES,
            CollectionSize.ANY,
            CollectionFeature.SERIALIZABLE)
        .createTestSuite();
  }

  static class TestSet<E> extends ForwardingSet<E> implements Serializable {
    final Set<E> delegate;
    public final Object mutex;

    public TestSet(Set<E> delegate, Object mutex) {
      checkNotNull(mutex);
      this.delegate = delegate;
      this.mutex = mutex;
    }

    @Override
    protected Set<E> delegate() {
      return delegate;
    }

    @Override
    public String toString() {
      assertTrue(Thread.holdsLock(mutex));
      return super.toString();
    }

    @Override
    public boolean equals(@NullableDecl Object o) {
      assertTrue(Thread.holdsLock(mutex));
      return super.equals(o);
    }

    @Override
    public int hashCode() {
      assertTrue(Thread.holdsLock(mutex));
      return super.hashCode();
    }

    @Override
    public boolean add(@NullableDecl E o) {
      assertTrue(Thread.holdsLock(mutex));
      return super.add(o);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
      assertTrue(Thread.holdsLock(mutex));
      return super.addAll(c);
    }

    @Override
    public void clear() {
      assertTrue(Thread.holdsLock(mutex));
      super.clear();
    }

    @Override
    public boolean contains(@NullableDecl Object o) {
      assertTrue(Thread.holdsLock(mutex));
      return super.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
      assertTrue(Thread.holdsLock(mutex));
      return super.containsAll(c);
    }

    @Override
    public boolean isEmpty() {
      assertTrue(Thread.holdsLock(mutex));
      return super.isEmpty();
    }

    /* Don't test iterator(); it may or may not hold the mutex. */

    @Override
    public boolean remove(@NullableDecl Object o) {
      assertTrue(Thread.holdsLock(mutex));
      return super.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
      assertTrue(Thread.holdsLock(mutex));
      return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
      assertTrue(Thread.holdsLock(mutex));
      return super.retainAll(c);
    }

    @Override
    public int size() {
      assertTrue(Thread.holdsLock(mutex));
      return super.size();
    }

    @Override
    public Object[] toArray() {
      assertTrue(Thread.holdsLock(mutex));
      return super.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
      assertTrue(Thread.holdsLock(mutex));
      return super.toArray(a);
    }

    private static final long serialVersionUID = 0;
  }
}
