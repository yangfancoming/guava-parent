package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link ReverseOrdering}.
 *
 * @author Chris Povirk
 */
public class ReverseOrdering_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader, ReverseOrdering<?> instance) {}

  @SuppressWarnings("unchecked") // deserialization is unsafe
  public static ReverseOrdering<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return new ReverseOrdering<>((Ordering<Object>) reader.readObject());
  }

  public static void serialize(SerializationStreamWriter writer, ReverseOrdering<?> instance)
      throws SerializationException {
    writer.writeObject(instance.forwardOrder);
  }
}
