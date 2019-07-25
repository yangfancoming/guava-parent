

package com.google.common.primitives;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@code UnsignedLong}.
 *
 * @author Louis Wasserman
 */
public class UnsignedLong_CustomFieldSerializer {
  public static void deserialize(SerializationStreamReader reader, UnsignedLong instance) {}

  public static UnsignedLong instantiate(SerializationStreamReader reader)
      throws SerializationException {
    return UnsignedLong.fromLongBits(reader.readLong());
  }

  public static void serialize(SerializationStreamWriter writer, UnsignedLong instance)
      throws SerializationException {
    writer.writeLong(instance.longValue());
  }
}
