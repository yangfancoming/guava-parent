package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link HashMultimap}.
 *
 * @author Jord Sonneveld
 */
public class HashMultimap_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader in, HashMultimap<?, ?> out) {}

  public static HashMultimap<Object, Object> instantiate(SerializationStreamReader in)
      throws SerializationException {
    return (HashMultimap<Object, Object>)
        Multimap_CustomFieldSerializerBase.populate(in, HashMultimap.create());
  }

  public static void serialize(SerializationStreamWriter out, HashMultimap<?, ?> multimap)
      throws SerializationException {
    Multimap_CustomFieldSerializerBase.serialize(out, multimap);
  }
}
