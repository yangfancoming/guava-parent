package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link NullsFirstOrdering}.
 *
 * @author Chris Povirk
 */
public class NullsFirstOrdering_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, NullsFirstOrdering<?> instance) {}

  @SuppressWarnings("unchecked") // deserialization is unsafe
  public static NullsFirstOrdering<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return new NullsFirstOrdering<>((Ordering<Object>) reader.readObject());
  }

  public static void serialize(SerializationStreamWriter writer, NullsFirstOrdering<?> instance)
      throws SerializationException {
    writer.writeObject(instance.ordering);
  }
}
