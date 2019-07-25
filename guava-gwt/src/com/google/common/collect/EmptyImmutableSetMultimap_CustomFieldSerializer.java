package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link EmptyImmutableSetMultimap}.
 *
 * @author Chris Povirk
 */
public class EmptyImmutableSetMultimap_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, EmptyImmutableSetMultimap instance) {}

  public static EmptyImmutableSetMultimap instantiate(SerializationStreamReader reader) {
    return EmptyImmutableSetMultimap.INSTANCE;
  }

  public static void serialize(
      SerializationStreamWriter writer, EmptyImmutableSetMultimap instance) {}
}
