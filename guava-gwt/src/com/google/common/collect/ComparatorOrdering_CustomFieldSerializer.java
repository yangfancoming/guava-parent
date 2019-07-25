package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import java.util.Comparator;

/**
 * This class implements the GWT serialization of {@link ComparatorOrdering}.
 *
 * @author Chris Povirk
 */
public class ComparatorOrdering_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, ComparatorOrdering<?> instance) {}

  @SuppressWarnings("unchecked") // deserialization is unsafe
  public static ComparatorOrdering<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return new ComparatorOrdering<>((Comparator<Object>) reader.readObject());
  }

  public static void serialize(SerializationStreamWriter writer, ComparatorOrdering<?> instance)
      throws SerializationException {
    writer.writeObject(instance.comparator);
  }
}
