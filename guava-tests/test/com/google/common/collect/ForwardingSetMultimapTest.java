

package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.testing.EqualsTester;
import com.google.common.testing.ForwardingWrapperTester;
import junit.framework.TestCase;

/**
 * Unit test for {@link ForwardingSetMultimap}.
 *
 * @author Kurt Alfred Kluever
 */
public class ForwardingSetMultimapTest extends TestCase {

  @SuppressWarnings("rawtypes")
  public void testForwarding() {
    new ForwardingWrapperTester()
        .testForwarding(
            SetMultimap.class,
            new Function<SetMultimap, SetMultimap>() {
              @Override
              public SetMultimap apply(SetMultimap delegate) {
                return wrap(delegate);
              }
            });
  }

  public void testEquals() {
    SetMultimap<Integer, String> map1 = ImmutableSetMultimap.of(1, "one");
    SetMultimap<Integer, String> map2 = ImmutableSetMultimap.of(2, "two");
    new EqualsTester()
        .addEqualityGroup(map1, wrap(map1), wrap(map1))
        .addEqualityGroup(map2, wrap(map2))
        .testEquals();
  }

  private static <K, V> SetMultimap<K, V> wrap(final SetMultimap<K, V> delegate) {
    return new ForwardingSetMultimap<K, V>() {
      @Override
      protected SetMultimap<K, V> delegate() {
        return delegate;
      }
    };
  }
}
