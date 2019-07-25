package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.core.java.util.Map_CustomFieldSerializerBase;

/**
 * This class contains static utility methods for writing {@link ImmutableTable} GWT field
 * serializers. Serializers should delegate to {@link #serialize} and {@link #instantiate}.
 *
 * @author Chris Povirk
 */
final class ImmutableTable_CustomFieldSerializerBase {
  public static ImmutableTable<Object, Object, Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    ImmutableTable.Builder<Object, Object, Object> builder = ImmutableTable.builder();
    int rowCount = reader.readInt();
    for (int i = 0; i < rowCount; i++) {
      Object rowKey = reader.readObject();
      int colCount = reader.readInt();
      for (int j = 0; j < colCount; j++) {
        builder.put(rowKey, reader.readObject(), reader.readObject());
      }
    }
    return builder.build();
  }

  public static void serialize(
      SerializationStreamWriter writer, ImmutableTable<Object, Object, Object> table)
      throws SerializationException {
    writer.writeInt(table.rowKeySet().size());
    for (Object rowKey : table.rowKeySet()) {
      writer.writeObject(rowKey);
      Map_CustomFieldSerializerBase.serialize(writer, table.row(rowKey));
    }
  }

  private ImmutableTable_CustomFieldSerializerBase() {}
}
