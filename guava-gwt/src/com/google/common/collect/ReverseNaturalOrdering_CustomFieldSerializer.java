package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link ReverseNaturalOrdering}.
 *
 * @author Chris Povirk
 */
public class ReverseNaturalOrdering_CustomFieldSerializer {

  public static void deserialize(
      SerializationStreamReader reader, ReverseNaturalOrdering instance) {}

  public static ReverseNaturalOrdering instantiate(SerializationStreamReader reader) {
    return ReverseNaturalOrdering.INSTANCE;
  }

  public static void serialize(SerializationStreamWriter writer, ReverseNaturalOrdering instance) {}
}
