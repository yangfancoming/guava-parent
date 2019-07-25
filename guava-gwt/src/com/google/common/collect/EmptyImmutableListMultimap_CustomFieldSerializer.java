package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link EmptyImmutableListMultimap}.
 *
 * @author Chris Povirk
 */
public class EmptyImmutableListMultimap_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, EmptyImmutableListMultimap instance) {}

  public static EmptyImmutableListMultimap instantiate(SerializationStreamReader reader) {
    return EmptyImmutableListMultimap.INSTANCE;
  }

  public static void serialize(
      SerializationStreamWriter writer, EmptyImmutableListMultimap instance) {}
}
