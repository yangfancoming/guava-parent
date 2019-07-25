package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link DenseImmutableTable}.
 *
 * @author Chris Povirk
 */
public class DenseImmutableTable_CustomFieldSerializer {
  public static void deserialize(
      SerializationStreamReader reader, DenseImmutableTable<?, ?, ?> instance) {}

  public static DenseImmutableTable<Object, Object, Object> instantiate(
      SerializationStreamReader reader) throws SerializationException {
    return (DenseImmutableTable<Object, Object, Object>)
        ImmutableTable_CustomFieldSerializerBase.instantiate(reader);
  }

  public static void serialize(
      SerializationStreamWriter writer, DenseImmutableTable<Object, Object, Object> table)
      throws SerializationException {
    ImmutableTable_CustomFieldSerializerBase.serialize(writer, table);
  }
}
