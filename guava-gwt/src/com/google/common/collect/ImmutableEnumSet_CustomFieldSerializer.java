package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.core.java.util.Collection_CustomFieldSerializerBase;
import java.util.List;

/**
 * This class implements the GWT serialization of {@link ImmutableEnumSet}.
 *
 * @author Hayward Chan
 */
public class ImmutableEnumSet_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader, ImmutableEnumSet<?> instance) {}

  public static <E extends Enum<E>> ImmutableEnumSet<?> instantiate(
      SerializationStreamReader reader) throws SerializationException {
    List<E> deserialized = Lists.newArrayList();
    Collection_CustomFieldSerializerBase.deserialize(reader, deserialized);
    /*
     * It is safe to cast to ImmutableEnumSet because in order for it to be
     * serialized as an ImmutableEnumSet, it must be non-empty to start
     * with.
     */
    return (ImmutableEnumSet<?>) Sets.immutableEnumSet(deserialized);
  }

  public static void serialize(SerializationStreamWriter writer, ImmutableEnumSet<?> instance)
      throws SerializationException {
    Collection_CustomFieldSerializerBase.serialize(writer, instance);
  }
}
