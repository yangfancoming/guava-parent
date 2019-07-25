package com.google.common.collect.testing.google;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ListMultimap;

/**
 * A generator for {@code ListMultimap} implementations based on test data.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
public interface TestListMultimapGenerator<K, V>
    extends TestMultimapGenerator<K, V, ListMultimap<K, V>> {}
