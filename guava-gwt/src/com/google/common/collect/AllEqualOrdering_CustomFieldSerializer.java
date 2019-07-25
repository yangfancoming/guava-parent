package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link AllEqualOrdering}.
 *
 * @author Chris Povirk
 */
public class AllEqualOrdering_CustomFieldSerializer {
  public static void deserialize(SerializationStreamReader reader, AllEqualOrdering instance) {}

  public static AllEqualOrdering instantiate(SerializationStreamReader reader) {
    return AllEqualOrdering.INSTANCE;
  }

  public static void serialize(SerializationStreamWriter writer, AllEqualOrdering instance) {}
}
