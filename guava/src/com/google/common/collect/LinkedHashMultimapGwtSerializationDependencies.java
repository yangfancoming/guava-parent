

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;

/**
 * A dummy superclass to support GWT serialization of the element types of a {@link
 * LinkedHashMultimap}. The GWT supersource for this class contains a field for each type.
 *
 * <p>For details about this hack, see {@link GwtSerializationDependencies}, which takes the same
 * approach but with a subclass rather than a superclass.
 *
 * <p>TODO(cpovirk): Consider applying this subclass approach to our other types.
 */
@GwtCompatible(emulated = true)
abstract class LinkedHashMultimapGwtSerializationDependencies<K, V>
    extends AbstractSetMultimap<K, V> {
  LinkedHashMultimapGwtSerializationDependencies(Map<K, Collection<V>> map) {
    super(map);
  }
}
