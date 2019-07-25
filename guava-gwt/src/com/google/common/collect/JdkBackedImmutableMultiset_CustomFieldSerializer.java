

package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link JdkBackedImmutableMultiset}.
 *
 * @author Louis Wasserman
 */
public class JdkBackedImmutableMultiset_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader, ImmutableMultiset<?> instance) {}

  public static ImmutableMultiset<Object> instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return ImmutableMultiset.copyOf(
        Multiset_CustomFieldSerializerBase.populate(reader, LinkedHashMultiset.create()));
  }

  public static void serialize(SerializationStreamWriter writer, ImmutableMultiset<?> instance)
      throws SerializationException {
    Multiset_CustomFieldSerializerBase.serialize(writer, instance);
  }
}
