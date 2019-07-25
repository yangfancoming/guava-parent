

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.SortedSet;
import junit.framework.TestCase;

/**
 * Unit tests for {@code SortedIterables}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
public class SortedIterablesTest extends TestCase {
  public void testSameComparator() {
    assertTrue(SortedIterables.hasSameComparator(Ordering.natural(), Sets.newTreeSet()));
    // Before JDK6 (including under GWT), the TreeMap keySet is a plain Set.
    if (Maps.newTreeMap().keySet() instanceof SortedSet) {
      assertTrue(SortedIterables.hasSameComparator(Ordering.natural(), Maps.newTreeMap().keySet()));
    }
    assertTrue(
        SortedIterables.hasSameComparator(
            Ordering.natural().reverse(), Sets.newTreeSet(Ordering.natural().reverse())));
  }

  public void testComparator() {
    assertEquals(Ordering.natural(), SortedIterables.comparator(Sets.newTreeSet()));
  }
}
