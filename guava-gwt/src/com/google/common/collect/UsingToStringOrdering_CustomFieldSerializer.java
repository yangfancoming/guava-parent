package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link UsingToStringOrdering}.
 *
 * @author Chris Povirk
 */
public class UsingToStringOrdering_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, UsingToStringOrdering instance) {}

  public static UsingToStringOrdering instantiate(SerializationStreamReader reader) {
    return UsingToStringOrdering.INSTANCE;
  }

  public static void serialize(SerializationStreamWriter writer, UsingToStringOrdering instance) {}
}
