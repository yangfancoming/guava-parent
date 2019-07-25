package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link ArrayListMultimap}.
 *
 * @author Chris Povirk
 */
public class ArrayListMultimap_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader in, ArrayListMultimap<?, ?> out) {}

  public static ArrayListMultimap<Object, Object> instantiate(SerializationStreamReader in)
      throws SerializationException {
    return (ArrayListMultimap<Object, Object>)
        Multimap_CustomFieldSerializerBase.populate(in, ArrayListMultimap.create());
  }

  public static void serialize(SerializationStreamWriter out, ArrayListMultimap<?, ?> multimap)
      throws SerializationException {
    Multimap_CustomFieldSerializerBase.serialize(out, multimap);
  }
}
