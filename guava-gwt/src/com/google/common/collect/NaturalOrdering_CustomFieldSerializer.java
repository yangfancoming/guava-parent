package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link NaturalOrdering}.
 *
 * @author Chris Povirk
 */
public class NaturalOrdering_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader, NaturalOrdering instance) {}

  public static NaturalOrdering instantiate(SerializationStreamReader reader) {
    return NaturalOrdering.INSTANCE;
  }

  public static void serialize(SerializationStreamWriter writer, NaturalOrdering instance) {}
}
