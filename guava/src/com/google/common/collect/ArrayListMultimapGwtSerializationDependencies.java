

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.Map;

/**
 * A dummy superclass to support GWT serialization of the element types of an {@link
 * ArrayListMultimap}. The GWT supersource for this class contains a field for each type.
 *
 * <p>For details about this hack, see {@link GwtSerializationDependencies}, which takes the same
 * approach but with a subclass rather than a superclass.
 *
 * <p>TODO(cpovirk): Consider applying this subclass approach to our other types.
 */
@GwtCompatible(emulated = true)
abstract class ArrayListMultimapGwtSerializationDependencies<K, V>
    extends AbstractListMultimap<K, V> {
  ArrayListMultimapGwtSerializationDependencies(Map<K, Collection<V>> map) {
    super(map);
  }
  // TODO(cpovirk): Maybe I should have just one shared superclass for AbstractMultimap itself?
}
