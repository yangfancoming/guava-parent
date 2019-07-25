package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.core.java.util.Map_CustomFieldSerializerBase;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * This class contains static utility methods for writing {@code ImmutableSortedMap} GWT field
 * serializers.
 *
 * @author Chris Povirk
 */
final class ImmutableSortedMap_CustomFieldSerializerBase {
  static ImmutableSortedMap<Object, Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    /*
     * Nothing we can do, but we're already assuming the serialized form is
     * correctly typed, anyway.
     */
    @SuppressWarnings("unchecked")
    Comparator<Object> comparator = (Comparator<Object>) reader.readObject();

    SortedMap<Object, Object> entries = new TreeMap<>(comparator);
    Map_CustomFieldSerializerBase.deserialize(reader, entries);

    return ImmutableSortedMap.orderedBy(comparator).putAll(entries).build();
  }

  static void serialize(SerializationStreamWriter writer, ImmutableSortedMap<?, ?> instance)
      throws SerializationException {
    writer.writeObject(instance.comparator());

    Map_CustomFieldSerializerBase.serialize(writer, instance);
  }

  private ImmutableSortedMap_CustomFieldSerializerBase() {}
}
