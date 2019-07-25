package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * Custom GWT serializer for {@link Absent}.
 *
 * <p>GWT can serialize an absent {@code Optional} on its own, but the resulting object is a
 * different instance than the singleton {@code Absent.INSTANCE}, which breaks equality. We
 * implement a custom serializer to maintain the singleton property.
 *
 * @author Chris Povirk
 */
@GwtCompatible
public class Absent_CustomFieldSerializer {
  public static void deserialize(SerializationStreamReader reader, Absent<?> instance) {}

  public static Absent<?> instantiate(SerializationStreamReader reader) {
    return Absent.INSTANCE;
  }

  public static void serialize(SerializationStreamWriter writer, Absent<?> instance) {}
}
