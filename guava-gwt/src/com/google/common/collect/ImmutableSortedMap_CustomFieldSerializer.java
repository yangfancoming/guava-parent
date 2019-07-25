package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * Even though {@link ImmutableSortedMap} cannot be instantiated, we still need a custom field
 * serializer. TODO(cpovirk): why? Does it help if ensure that the GWT and non-GWT classes have the
 * same fields? Is that worth the trouble?
 *
 * @author Chris Povirk
 */
public final class ImmutableSortedMap_CustomFieldSerializer {
  public static void deserialize(
      SerializationStreamReader reader, ImmutableSortedMap<?, ?> instance) {}

  public static ImmutableSortedMap<?, ?> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return ImmutableSortedMap_CustomFieldSerializerBase.instantiate(reader);
  }

  public static void serialize(SerializationStreamWriter writer, ImmutableSortedMap<?, ?> instance)
      throws SerializationException {
    ImmutableSortedMap_CustomFieldSerializerBase.serialize(writer, instance);
  }
}
