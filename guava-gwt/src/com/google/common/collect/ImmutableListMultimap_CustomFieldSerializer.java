package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link ImmutableListMultimap}.
 *
 * @author Chris Povirk
 */
public class ImmutableListMultimap_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, ImmutableListMultimap<?, ?> instance) {}

  public static ImmutableListMultimap<Object, Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return (ImmutableListMultimap<Object, Object>)
        Multimap_CustomFieldSerializerBase.instantiate(reader, ImmutableListMultimap.builder());
  }

  public static void serialize(
      SerializationStreamWriter writer, ImmutableListMultimap<?, ?> instance)
      throws SerializationException {
    Multimap_CustomFieldSerializerBase.serialize(writer, instance);
  }
}
