package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link SingletonImmutableBiMap}.
 *
 * @author Chris Povirk
 */
public class SingletonImmutableBiMap_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, SingletonImmutableBiMap<?, ?> instance) {}

  public static SingletonImmutableBiMap<Object, Object> instantiate(
      SerializationStreamReader reader) throws SerializationException {
    Object key = checkNotNull(reader.readObject());
    Object value = checkNotNull(reader.readObject());
    return new SingletonImmutableBiMap<>(key, value);
  }

  public static void serialize(
      SerializationStreamWriter writer, SingletonImmutableBiMap<?, ?> instance)
      throws SerializationException {
    writer.writeObject(instance.singleKey);
    writer.writeObject(instance.singleValue);
  }
}
