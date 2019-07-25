package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link LinkedHashMultiset}.
 *
 * @author Chris Povirk
 */
public class LinkedHashMultiset_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, LinkedHashMultiset<?> instance) {}

  public static LinkedHashMultiset<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return (LinkedHashMultiset<Object>)
        Multiset_CustomFieldSerializerBase.populate(reader, LinkedHashMultiset.create());
  }

  public static void serialize(SerializationStreamWriter writer, LinkedHashMultiset<?> instance)
      throws SerializationException {
    Multiset_CustomFieldSerializerBase.serialize(writer, instance);
  }
}
