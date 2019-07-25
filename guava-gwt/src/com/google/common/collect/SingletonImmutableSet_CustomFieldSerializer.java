package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link SingletonImmutableSet}.
 *
 * @author Hayward Chan
 */
public class SingletonImmutableSet_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, SingletonImmutableSet<?> instance) {}

  public static SingletonImmutableSet<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    Object element = reader.readObject();
    return new SingletonImmutableSet<>(element);
  }

  public static void serialize(SerializationStreamWriter writer, SingletonImmutableSet<?> instance)
      throws SerializationException {
    writer.writeObject(instance.element);
  }
}
