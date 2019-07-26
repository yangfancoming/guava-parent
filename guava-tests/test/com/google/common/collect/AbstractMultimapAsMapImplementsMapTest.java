

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.MapInterfaceTest;
import java.util.Collection;
import java.util.Map;

/**
 * Test {@link Multimap#asMap()} for an arbitrary multimap with {@link MapInterfaceTest}.
 *
 * @author George van den Driessche
 * @author Jared Levy
 */
@GwtCompatible
public abstract class AbstractMultimapAsMapImplementsMapTest
    extends MapInterfaceTest<String, Collection<Integer>> {

  public AbstractMultimapAsMapImplementsMapTest(
      boolean modifiable, boolean allowsNulls, boolean supportsIteratorRemove) {
    super(allowsNulls, allowsNulls, false, modifiable, modifiable, supportsIteratorRemove);
  }

  protected void populate(Multimap<String, Integer> multimap) {
    multimap.put("one", 1);
    multimap.put("two", 2);
    multimap.put("two", 22);
    multimap.put("three", 3);
    multimap.put("three", 33);
    multimap.put("three", 333);
  }

  @Override
  protected String getKeyNotInPopulatedMap() throws UnsupportedOperationException {
    return "zero";
  }

  @Override
  protected Collection<Integer> getValueNotInPopulatedMap() throws UnsupportedOperationException {
    return Lists.newArrayList(0);
  }

  /**
   * The version of this test supplied by {@link MapInterfaceTest} fails for this particular Map
   * implementation, because {@code map.get()} returns a view collection that changes in the course
   * of a call to {@code remove()}. Thus, the expectation doesn't hold that {@code map.remove(x)}
   * returns the same value which {@code map.get(x)} did immediately beforehand.
   */
  @Override
  public void testRemove() {
    final Map<String, Collection<Integer>> map;
    final String keyToRemove;
    try {
      map = makePopulatedMap();
    } catch (UnsupportedOperationException e) {
      return;
    }
    keyToRemove = map.keySet().iterator().next();
    if (supportsRemove) {
      int initialSize = map.size();
      map.get(keyToRemove);
      map.remove(keyToRemove);
      // This line doesn't hold - see the Javadoc comments above.
      // assertEquals(expectedValue, oldValue);
      assertFalse(map.containsKey(keyToRemove));
      assertEquals(initialSize - 1, map.size());
    } else {
      try {
        map.remove(keyToRemove);
        fail("Expected UnsupportedOperationException.");
      } catch (UnsupportedOperationException expected) {
      }
    }
    assertInvariants(map);
  }
}
