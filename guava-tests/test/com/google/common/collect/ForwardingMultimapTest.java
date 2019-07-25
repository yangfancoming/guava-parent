package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.testing.EqualsTester;
import com.google.common.testing.ForwardingWrapperTester;
import junit.framework.TestCase;

/**
 * Unit test for {@link ForwardingMultimap}.
 *
 * @author Hayward Chan
 */
public class ForwardingMultimapTest extends TestCase {

  @SuppressWarnings("rawtypes")
  public void testForwarding() {
    new ForwardingWrapperTester()
        .testForwarding(
            Multimap.class,
            new Function<Multimap, Multimap>() {
              @Override
              public Multimap apply(Multimap delegate) {
                return wrap(delegate);
              }
            });
  }

  public void testEquals() {
    Multimap<Integer, String> map1 = ImmutableMultimap.of(1, "one");
    Multimap<Integer, String> map2 = ImmutableMultimap.of(2, "two");
    new EqualsTester()
        .addEqualityGroup(map1, wrap(map1), wrap(map1))
        .addEqualityGroup(map2, wrap(map2))
        .testEquals();
  }

  private static <K, V> Multimap<K, V> wrap(final Multimap<K, V> delegate) {
    return new ForwardingMultimap<K, V>() {
      @Override
      protected Multimap<K, V> delegate() {
        return delegate;
      }
    };
  }
}
