package com.google.common.collect.testing.google;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.SetMultimap;

/**
 * A generator for {@code SetMultimap} implementations based on test data.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
public interface TestSetMultimapGenerator<K, V>
    extends TestMultimapGenerator<K, V, SetMultimap<K, V>> {}
