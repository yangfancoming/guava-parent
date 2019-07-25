package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.Map.Entry;
import java.util.SortedMap;

/**
 * Creates sorted maps, containing sample elements, to be tested.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
public interface TestSortedMapGenerator<K, V> extends TestMapGenerator<K, V> {
  @Override
  SortedMap<K, V> create(Object... elements);

  /**
   * Returns an entry with a key less than the keys of the {@link #samples()} and less than the key
   * of {@link #belowSamplesGreater()}.
   */
  Entry<K, V> belowSamplesLesser();

  /**
   * Returns an entry with a key less than the keys of the {@link #samples()} but greater than the
   * key of {@link #belowSamplesLesser()}.
   */
  Entry<K, V> belowSamplesGreater();

  /**
   * Returns an entry with a key greater than the keys of the {@link #samples()} but less than the
   * key of {@link #aboveSamplesGreater()}.
   */
  Entry<K, V> aboveSamplesLesser();

  /**
   * Returns an entry with a key greater than the keys of the {@link #samples()} and greater than
   * the key of {@link #aboveSamplesLesser()}.
   */
  Entry<K, V> aboveSamplesGreater();
}
