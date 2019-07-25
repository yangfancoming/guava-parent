package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link HashMultiset}.
 *
 * @author Chris Povirk
 */
public class HashMultiset_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader, HashMultiset<?> instance) {}

  public static HashMultiset<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return (HashMultiset<Object>)
        Multiset_CustomFieldSerializerBase.populate(reader, HashMultiset.create());
  }

  public static void serialize(SerializationStreamWriter writer, HashMultiset<?> instance)
      throws SerializationException {
    Multiset_CustomFieldSerializerBase.serialize(writer, instance);
  }
}
