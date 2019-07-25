

package com.google.common.collect;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

/**
 * This class implements the GWT serialization of {@link Range}.
 *
 * @author Dean de Bree
 */
public class Range_CustomFieldSerializer {

  public static void deserialize(SerializationStreamReader reader, Range<?> instance) {}

  public static Range<?> instantiate(SerializationStreamReader reader)
      throws SerializationException {

    Cut lowerBound;
    boolean hasLowerBound = reader.readBoolean();
    if (hasLowerBound) {
      boolean lowerIsClosed = reader.readBoolean();
      Comparable lower = (Comparable) reader.readObject();

      lowerBound = lowerIsClosed ? Cut.belowValue(lower) : Cut.aboveValue(lower);
    } else {
      lowerBound = Cut.belowAll();
    }

    Cut upperBound;
    boolean hasUpperBound = reader.readBoolean();
    if (hasUpperBound) {
      boolean upperIsClosed = reader.readBoolean();
      Comparable upper = (Comparable) reader.readObject();

      upperBound = upperIsClosed ? Cut.aboveValue(upper) : Cut.belowValue(upper);
    } else {
      upperBound = Cut.aboveAll();
    }

    return Range.create(lowerBound, upperBound);
  }

  public static void serialize(SerializationStreamWriter writer, Range<?> instance)
      throws SerializationException {

    if (instance.hasLowerBound()) {
      writer.writeBoolean(true);
      writer.writeBoolean(instance.lowerBoundType() == BoundType.CLOSED);
      writer.writeObject(instance.lowerEndpoint());
    } else {
      writer.writeBoolean(false);
    }

    if (instance.hasUpperBound()) {
      writer.writeBoolean(true);
      writer.writeBoolean(instance.upperBoundType() == BoundType.CLOSED);
      writer.writeObject(instance.upperEndpoint());
    } else {
      writer.writeBoolean(false);
    }
  }
}
