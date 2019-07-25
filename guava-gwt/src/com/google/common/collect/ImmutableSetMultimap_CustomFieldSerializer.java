package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import java.util.Comparator;

/**
 * This class implements the GWT serialization of {@link ImmutableSetMultimap}.
 *
 * @author Chris Povirk
 */
public class ImmutableSetMultimap_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, ImmutableSetMultimap<?, ?> instance) {}

  // Serialization type safety is at the caller's mercy.
  @SuppressWarnings("unchecked")
  public static ImmutableSetMultimap<Object, Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    Comparator<Object> valueComparator = (Comparator<Object>) reader.readObject();
    ImmutableSetMultimap.Builder<Object, Object> builder = ImmutableSetMultimap.builder();
    if (valueComparator != null) {
      builder.orderValuesBy(valueComparator);
    }
    return (ImmutableSetMultimap<Object, Object>)
        Multimap_CustomFieldSerializerBase.instantiate(reader, builder);
  }

  public static void serialize(
      SerializationStreamWriter writer, ImmutableSetMultimap<?, ?> instance)
      throws SerializationException {
    writer.writeObject(instance.valueComparator());
    Multimap_CustomFieldSerializerBase.serialize(writer, instance);
  }
}
