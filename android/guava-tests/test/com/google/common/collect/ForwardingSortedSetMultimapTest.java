

package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.testing.EqualsTester;
import com.google.common.testing.ForwardingWrapperTester;
import junit.framework.TestCase;

/**
 * Unit test for {@link ForwardingSortedSetMultimap}.
 *
 * @author Kurt Alfred Kluever
 */
public class ForwardingSortedSetMultimapTest extends TestCase {

  @SuppressWarnings("rawtypes")
  public void testForwarding() {
    new ForwardingWrapperTester()
        .testForwarding(
            SortedSetMultimap.class,
            new Function<SortedSetMultimap, SortedSetMultimap>() {
              @Override
              public SortedSetMultimap apply(SortedSetMultimap delegate) {
                return wrap(delegate);
              }
            });
  }

  public void testEquals() {
    SortedSetMultimap<Integer, String> map1 = TreeMultimap.create(ImmutableMultimap.of(1, "one"));
    SortedSetMultimap<Integer, String> map2 = TreeMultimap.create(ImmutableMultimap.of(2, "two"));
    new EqualsTester()
        .addEqualityGroup(map1, wrap(map1), wrap(map1))
        .addEqualityGroup(map2, wrap(map2))
        .testEquals();
  }

  private static <K, V> SortedSetMultimap<K, V> wrap(final SortedSetMultimap<K, V> delegate) {
    return new ForwardingSortedSetMultimap<K, V>() {
      @Override
      protected SortedSetMultimap<K, V> delegate() {
        return delegate;
      }
    };
  }
}
