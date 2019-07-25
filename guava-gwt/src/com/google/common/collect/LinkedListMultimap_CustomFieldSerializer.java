package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import java.util.Map.Entry;

/**
 * This class implements the GWT serialization of {@link LinkedListMultimap}.
 *
 * @author Chris Povirk
 */
public class LinkedListMultimap_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader in, LinkedListMultimap<?, ?> out) {}

  public static LinkedListMultimap<Object, Object> instantiate(SerializationStreamReader in)
      throws SerializationException {
    LinkedListMultimap<Object, Object> multimap = LinkedListMultimap.create();
    int size = in.readInt();
    for (int i = 0; i < size; i++) {
      Object key = in.readObject();
      Object value = in.readObject();
      multimap.put(key, value);
    }
    return multimap;
  }

  public static void serialize(SerializationStreamWriter out, LinkedListMultimap<?, ?> multimap)
      throws SerializationException {
    out.writeInt(multimap.size());
    for (Entry<?, ?> entry : multimap.entries()) {
      out.writeObject(entry.getKey());
      out.writeObject(entry.getValue());
    }
  }
}
