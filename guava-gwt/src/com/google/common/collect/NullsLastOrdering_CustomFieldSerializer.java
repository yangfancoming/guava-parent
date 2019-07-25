package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link NullsLastOrdering}.
 *
 * @author Chris Povirk
 */
public class NullsLastOrdering_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader, NullsLastOrdering<?> instance) {}

  @SuppressWarnings("unchecked") // deserialization is unsafe
  public static NullsLastOrdering<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return new NullsLastOrdering<>((Ordering<Object>) reader.readObject());
  }

  public static void serialize(SerializationStreamWriter writer, NullsLastOrdering<?> instance)
      throws SerializationException {
    writer.writeObject(instance.ordering);
  }
}
