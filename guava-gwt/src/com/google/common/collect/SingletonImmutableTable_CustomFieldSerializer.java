

package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link SingletonImmutableTable}.
 *
 * @author Chris Povirk
 */
public class SingletonImmutableTable_CustomFieldSerializer {
  public static void deserialize(
      SerializationStreamReader reader, SingletonImmutableTable<?, ?, ?> instance) {}

  public static SingletonImmutableTable<Object, Object, Object> instantiate(
      SerializationStreamReader reader) throws SerializationException {
    Object rowKey = reader.readObject();
    Object columnKey = reader.readObject();
    Object value = reader.readObject();
    return new SingletonImmutableTable<>(rowKey, columnKey, value);
  }

  public static void serialize(
      SerializationStreamWriter writer, SingletonImmutableTable<?, ?, ?> instance)
      throws SerializationException {
    writer.writeObject(instance.singleRowKey);
    writer.writeObject(instance.singleColumnKey);
    writer.writeObject(instance.singleValue);
  }
}
