package com.google.common.collect;

import static com.google.common.collect.Iterables.getOnlyElement;
import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.testing.MapTestSuiteBuilder;
import com.google.common.collect.testing.TestStringMapGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.MapFeature;
import java.util.Map;
import java.util.Map.Entry;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests for {@code CompactHashMap}.
 *
 * @author Louis Wasserman
 */
public class CompactHashMapTest extends TestCase {
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(
        MapTestSuiteBuilder.using(
                new TestStringMapGenerator() {
                  @Override
                  protected Map<String, String> create(Entry<String, String>[] entries) {
                    Map<String, String> map = CompactHashMap.create();
                    for (Entry<String, String> entry : entries) {
                      map.put(entry.getKey(), entry.getValue());
                    }
                    return map;
                  }
                })
            .named("CompactHashMap")
            .withFeatures(
                CollectionSize.ANY,
                MapFeature.GENERAL_PURPOSE,
                MapFeature.ALLOWS_NULL_KEYS,
                MapFeature.ALLOWS_NULL_VALUES,
                CollectionFeature.SERIALIZABLE,
                CollectionFeature.SUPPORTS_ITERATOR_REMOVE)
            .createTestSuite());
    suite.addTestSuite(CompactHashMapTest.class);
    return suite;
  }

  public void testTrimToSize() {
    CompactHashMap<Integer, String> map = CompactHashMap.createWithExpectedSize(100);
    for (int i = 0; i < 10; i++) {
      map.put(i, Integer.toString(i));
    }
    map.trimToSize();
    assertThat(map.entries).hasLength(10);
    assertThat(map.keys).hasLength(10);
    assertThat(map.values).hasLength(10);
    assertEquals(10, map.size());
    for (int i = 0; i < 10; i++) {
      assertEquals(Integer.toString(i), map.get(i));
    }
  }

  public void testEntrySetValueAfterRemoved() {
    CompactHashMap<Integer, String> map = CompactHashMap.create();
    map.put(1, "1");
    Entry<Integer, String> entry = getOnlyElement(map.entrySet());
    map.remove(1);
    entry.setValue("one");
    assertThat(map).containsEntry(1, "one");
  }
}
