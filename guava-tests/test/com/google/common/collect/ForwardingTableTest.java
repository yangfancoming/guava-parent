package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.testing.EqualsTester;
import com.google.common.testing.ForwardingWrapperTester;
import junit.framework.TestCase;

/**
 * Tests {@link ForwardingTable}.
 *
 * @author Gregory Kick
 */
public class ForwardingTableTest extends TestCase {

  @SuppressWarnings("rawtypes")
  public void testForwarding() {
    new ForwardingWrapperTester()
        .testForwarding(
            Table.class,
            new Function<Table, Table>() {
              @Override
              public Table apply(Table delegate) {
                return wrap(delegate);
              }
            });
  }

  public void testEquals() {
    Table<Integer, Integer, String> table1 = ImmutableTable.of(1, 1, "one");
    Table<Integer, Integer, String> table2 = ImmutableTable.of(2, 2, "two");
    new EqualsTester()
        .addEqualityGroup(table1, wrap(table1), wrap(table1))
        .addEqualityGroup(table2, wrap(table2))
        .testEquals();
  }

  private static <R, C, V> Table<R, C, V> wrap(final Table<R, C, V> delegate) {
    return new ForwardingTable<R, C, V>() {
      @Override
      protected Table<R, C, V> delegate() {
        return delegate;
      }
    };
  }
}
