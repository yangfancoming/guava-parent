

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Table.Cell;
import com.google.common.testing.EqualsTester;
import com.google.common.testing.SerializableTester;
import junit.framework.TestCase;

/**
 * Tests for {@link Tables}.
 *
 * @author Jared Levy
 */
@GwtCompatible(emulated = true)
public class TablesTest extends TestCase {

  @GwtIncompatible // SerializableTester
  public void testImmutableEntrySerialization() {
    Cell<String, Integer, Character> entry = Tables.immutableCell("foo", 1, 'a');
    SerializableTester.reserializeAndAssert(entry);
  }

  public void testImmutableEntryToString() {
    Cell<String, Integer, Character> entry = Tables.immutableCell("foo", 1, 'a');
    assertEquals("(foo,1)=a", entry.toString());

    Cell<String, Integer, Character> nullEntry = Tables.immutableCell(null, null, null);
    assertEquals("(null,null)=null", nullEntry.toString());
  }

  public void testEntryEquals() {
    Cell<String, Integer, Character> entry = Tables.immutableCell("foo", 1, 'a');

    new EqualsTester()
        .addEqualityGroup(entry, Tables.immutableCell("foo", 1, 'a'))
        .addEqualityGroup(Tables.immutableCell("bar", 1, 'a'))
        .addEqualityGroup(Tables.immutableCell("foo", 2, 'a'))
        .addEqualityGroup(Tables.immutableCell("foo", 1, 'b'))
        .addEqualityGroup(Tables.immutableCell(null, null, null))
        .testEquals();
  }

  public void testEntryEqualsNull() {
    Cell<String, Integer, Character> entry = Tables.immutableCell(null, null, null);

    new EqualsTester()
        .addEqualityGroup(entry, Tables.immutableCell(null, null, null))
        .addEqualityGroup(Tables.immutableCell("bar", null, null))
        .addEqualityGroup(Tables.immutableCell(null, 2, null))
        .addEqualityGroup(Tables.immutableCell(null, null, 'b'))
        .addEqualityGroup(Tables.immutableCell("foo", 1, 'a'))
        .testEquals();
  }
}
