package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link ExplicitOrdering}.
 *
 * @author Chris Povirk
 */
public class ExplicitOrdering_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader, ExplicitOrdering<?> instance) {}

  @SuppressWarnings("unchecked") // deserialization is unsafe
  public static ExplicitOrdering<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return new ExplicitOrdering<>((ImmutableMap<Object, Integer>) reader.readObject());
  }

  public static void serialize(SerializationStreamWriter writer, ExplicitOrdering<?> instance)
      throws SerializationException {
    writer.writeObject(instance.rankMap);
  }
}
