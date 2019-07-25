package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import java.util.Comparator;

/**
 * This class implements the GWT serialization of {@link TreeMultimap}.
 *
 * @author Nikhil Singhal
 */
public class TreeMultimap_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader in, TreeMultimap<?, ?> out) {}

  @SuppressWarnings("unchecked")
  public static TreeMultimap<Object, Object> instantiate(SerializationStreamReader in)
      throws SerializationException {
    Comparator<Object> keyComparator = (Comparator<Object>) in.readObject();
    Comparator<Object> valueComparator = (Comparator<Object>) in.readObject();

    return (TreeMultimap<Object, Object>)
        Multimap_CustomFieldSerializerBase.populate(
            in, TreeMultimap.create(keyComparator, valueComparator));
  }

  public static void serialize(SerializationStreamWriter out, TreeMultimap<?, ?> multimap)
      throws SerializationException {
    out.writeObject(multimap.keyComparator());
    out.writeObject(multimap.valueComparator());
    Multimap_CustomFieldSerializerBase.serialize(out, multimap);
  }
}
