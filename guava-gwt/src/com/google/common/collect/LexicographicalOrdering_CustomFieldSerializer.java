package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link LexicographicalOrdering}.
 *
 * @author Chris Povirk
 */
public class LexicographicalOrdering_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, LexicographicalOrdering<?> instance) {}

  @SuppressWarnings("unchecked") // deserialization is unsafe
  public static LexicographicalOrdering<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return new LexicographicalOrdering<>((Ordering<Object>) reader.readObject());
  }

  public static void serialize(
      SerializationStreamWriter writer, LexicographicalOrdering<?> instance)
      throws SerializationException {
    writer.writeObject(instance.elementOrder);
  }
}
