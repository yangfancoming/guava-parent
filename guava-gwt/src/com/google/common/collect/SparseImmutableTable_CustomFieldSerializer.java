package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link SparseImmutableTable}.
 *
 * @author Chris Povirk
 */
public class SparseImmutableTable_CustomFieldSerializer {
  public static void deserialize(
      SerializationStreamReader reader, SparseImmutableTable<?, ?, ?> instance) {}

  public static SparseImmutableTable<Object, Object, Object> instantiate(
      SerializationStreamReader reader) throws SerializationException {
    return (SparseImmutableTable<Object, Object, Object>)
        ImmutableTable_CustomFieldSerializerBase.instantiate(reader);
  }

  public static void serialize(
      SerializationStreamWriter writer, SparseImmutableTable<Object, Object, Object> table)
      throws SerializationException {
    ImmutableTable_CustomFieldSerializerBase.serialize(writer, table);
  }
}
