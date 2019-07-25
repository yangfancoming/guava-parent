

package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class contains static utility methods for writing {@link Table} GWT field serializers.
 * Serializers should delegate to {@link #serialize} and {@link #populate}.
 *
 * <p>For {@link ImmutableTable}, see {@link ImmutableTable_CustomFieldSerializerBase}.
 *
 * @author Chris Povirk
 */
final class Table_CustomFieldSerializerBase {
  static <T extends StandardTable<Object, Object, Object>> T populate(
      SerializationStreamReader reader, T table) throws SerializationException {
    Map<?, ?> hashMap = (Map<?, ?>) reader.readObject();
    for (Entry<?, ?> row : hashMap.entrySet()) {
      table.row(row.getKey()).putAll((Map<?, ?>) row.getValue());
    }
    return table;
  }

  static void serialize(SerializationStreamWriter writer, StandardTable<?, ?, ?> table)
      throws SerializationException {
    /*
     * The backing map of a {Hash,Tree}BasedTable is a {Hash,Tree}Map of {Hash,Tree}Maps. Therefore,
     * the backing map is serializable (assuming that the row, column and values, along with any
     * comparators, are all serializable).
     */
    writer.writeObject(table.backingMap);
  }

  private Table_CustomFieldSerializerBase() {}
}
