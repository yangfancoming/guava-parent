package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link ImmutableEntry}.
 *
 * @author Kyle Butt
 */
public class ImmutableEntry_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader, ImmutableEntry<?, ?> instance) {}

  public static ImmutableEntry<Object, Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    Object key = reader.readObject();
    Object value = reader.readObject();
    return new ImmutableEntry<>(key, value);
  }

  public static void serialize(SerializationStreamWriter writer, ImmutableEntry<?, ?> instance)
      throws SerializationException {
    writer.writeObject(instance.getKey());
    writer.writeObject(instance.getValue());
  }
}
