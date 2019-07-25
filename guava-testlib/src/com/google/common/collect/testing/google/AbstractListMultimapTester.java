package com.google.common.collect.testing.google;

import static com.google.common.collect.testing.Helpers.assertEqualInOrder;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ListMultimap;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Ignore;

/**
 * Superclass for all {@code ListMultimap} testers.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class AbstractListMultimapTester<K, V>
    extends AbstractMultimapTester<K, V, ListMultimap<K, V>> {

  @Override
  protected void assertGet(K key, V... values) {
    assertGet(key, Arrays.asList(values));
  }

  @Override
  protected void assertGet(K key, Collection<V> values) {
    assertEqualInOrder(values, multimap().get(key));

    if (!values.isEmpty()) {
      assertEqualInOrder(values, multimap().asMap().get(key));
      assertFalse(multimap().isEmpty());
    } else {
      assertNull(multimap().asMap().get(key));
    }

    assertEquals(values.size(), multimap().get(key).size());
    assertEquals(values.size() > 0, multimap().containsKey(key));
    assertEquals(values.size() > 0, multimap().keySet().contains(key));
    assertEquals(values.size() > 0, multimap().keys().contains(key));
  }
}
