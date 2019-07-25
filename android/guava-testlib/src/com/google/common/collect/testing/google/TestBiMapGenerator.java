package com.google.common.collect.testing.google;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.BiMap;
import com.google.common.collect.testing.TestContainerGenerator;
import java.util.Map.Entry;

/**
 * Creates bimaps, containing sample entries, to be tested.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
public interface TestBiMapGenerator<K, V> extends TestContainerGenerator<BiMap<K, V>, Entry<K, V>> {
  K[] createKeyArray(int length);

  V[] createValueArray(int length);
}
