

package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import java.util.Comparator;

/**
 * This class implements the GWT serialization of {@link TreeBasedTable}.
 *
 * @author Hayward Chan
 */
public class TreeBasedTable_CustomFieldSerializer {
  public static void deserialize(SerializationStreamReader reader, TreeBasedTable<?, ?, ?> table) {}

  public static TreeBasedTable<Object, Object, Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    @SuppressWarnings("unchecked") // The comparator isn't used statically.
    Comparator<Object> rowComparator = (Comparator<Object>) reader.readObject();
    @SuppressWarnings("unchecked") // The comparator isn't used statically.
    Comparator<Object> columnComparator = (Comparator<Object>) reader.readObject();

    TreeBasedTable<Object, Object, Object> table =
        TreeBasedTable.create(rowComparator, columnComparator);
    return Table_CustomFieldSerializerBase.populate(reader, table);
  }

  public static void serialize(SerializationStreamWriter writer, TreeBasedTable<?, ?, ?> table)
      throws SerializationException {
    writer.writeObject(table.rowComparator());
    writer.writeObject(table.columnComparator());
    Table_CustomFieldSerializerBase.serialize(writer, table);
  }
}
