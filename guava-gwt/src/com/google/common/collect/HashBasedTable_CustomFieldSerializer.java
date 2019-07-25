

package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link HashBasedTable}.
 *
 * @author Hayward Chan
 */
public class HashBasedTable_CustomFieldSerializer {
  public static void deserialize(SerializationStreamReader reader, HashBasedTable<?, ?, ?> table) {}

  public static HashBasedTable<Object, Object, Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return Table_CustomFieldSerializerBase.populate(reader, HashBasedTable.create());
  }

  public static void serialize(SerializationStreamWriter writer, HashBasedTable<?, ?, ?> table)
      throws SerializationException {
    Table_CustomFieldSerializerBase.serialize(writer, table);
  }
}
