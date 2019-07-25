

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import java.util.SortedMap;

/**
 * Tests for {@link Maps#transformValues(SortedMap, Function)}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
public class MapsSortedTransformValuesTest extends MapsTransformValuesTest {

  @Override
  protected SortedMap<String, String> makeEmptyMap() {
    return Maps.transformValues(Maps.<String, String>newTreeMap(), Functions.<String>identity());
  }

  @Override
  protected SortedMap<String, String> makePopulatedMap() {
    SortedMap<String, Integer> underlying = Maps.newTreeMap();
    underlying.put("a", 1);
    underlying.put("b", 2);
    underlying.put("c", 3);
    return Maps.transformValues(underlying, Functions.toStringFunction());
  }
}
