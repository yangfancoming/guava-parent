package com.google.common.collect;

import com.google.common.base.Function;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link ByFunctionOrdering}.
 *
 * @author Chris Povirk
 */
public class ByFunctionOrdering_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, ByFunctionOrdering<?, ?> instance) {}

  @SuppressWarnings("unchecked") // deserialization is unsafe
  public static ByFunctionOrdering<Object, Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return new ByFunctionOrdering<>(
        (Function<Object, Object>) reader.readObject(), (Ordering<Object>) reader.readObject());
  }

  public static void serialize(SerializationStreamWriter writer, ByFunctionOrdering<?, ?> instance)
      throws SerializationException {
    writer.writeObject(instance.function);
    writer.writeObject(instance.ordering);
  }
}
