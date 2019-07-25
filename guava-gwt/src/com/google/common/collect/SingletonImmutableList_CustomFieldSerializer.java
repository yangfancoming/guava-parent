package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link SingletonImmutableList}.
 *
 * @author Chris Povirk
 */
public class SingletonImmutableList_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, SingletonImmutableList<?> instance) {}

  public static SingletonImmutableList<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    Object element = reader.readObject();
    return new SingletonImmutableList<>(element);
  }

  public static void serialize(SerializationStreamWriter writer, SingletonImmutableList<?> instance)
      throws SerializationException {
    writer.writeObject(instance.element);
  }
}
