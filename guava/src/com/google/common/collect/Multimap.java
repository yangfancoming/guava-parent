

package com.google.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A collection that maps keys to values, similar to {@link Map}, but in which each key may be
 * associated with <i>multiple</i> values. You can visualize the contents of a multimap either as a
 * map from keys to <i>nonempty</i> collections of values:
 *
 * <ul>
 *   <li>a → 1, 2
 *   <li>b → 3
 * </ul>
 *
 * ... or as a single "flattened" collection of key-value pairs:
 *
 * <ul>
 *   <li>a → 1
 *   <li>a → 2
 *   <li>b → 3
 * </ul>
 *
 * <b>Important:</b> although the first interpretation resembles how most multimaps are
 * <i>implemented</i>, the design of the {@code Multimap} API is based on the <i>second</i> form.
 * So, using the multimap shown above as an example, the {@link #size} is {@code 3}, not {@code 2},
 * and the {@link #values} collection is {@code [1, 2, 3]}, not {@code [[1, 2], [3]]}. For those
 * times when the first style is more useful, use the multimap's {@link #asMap} view (or create a
 * {@code Map<K, Collection<V>>} in the first place).
 *
 * <h3>Example</h3>
 *
 * The following code:
 *
 * <pre>{@code
 * ListMultimap<String, String> multimap = ArrayListMultimap.create();
 * for (President pres : US_PRESIDENTS_IN_ORDER) {
 *   multimap.put(pres.firstName(), pres.lastName());
 * }
 * for (String firstName : multimap.keySet()) {
 *   List<String> lastNames = multimap.get(firstName);
 *   out.println(firstName + ": " + lastNames);
 * }
 * }</pre>
 *
 * ... produces output such as:
 *
 * <pre>{@code
 * Zachary: [Taylor]
 * John: [Adams, Adams, Tyler, Kennedy]  // Remember, Quincy!
 * George: [Washington, Bush, Bush]
 * Grover: [Cleveland, Cleveland]        // Two, non-consecutive terms, rep'ing NJ!
 * ...
 * }</pre>
 *
 * <h3>Views</h3>
 *
 * Much of the power of the multimap API comes from the <i>view collections</i> it provides.
 * These always reflect the latest state of the multimap itself. When they support modification, the
 * changes are <i>write-through</i> (they automatically update the backing multimap). These view
 * collections are:
 *
 * <ul>
 *   <li>{@link #asMap}, mentioned above
 *   <li>{@link #keys}, {@link #keySet}, {@link #values}, {@link #entries}, which are similar to the
 *       corresponding view collections of {@link Map}
 *   <li>and, notably, even the collection returned by {@link #get get(key)} is an active view of
 *       the values corresponding to {@code key}
 * </ul>
 *
 * The collections returned by the {@link #replaceValues replaceValues} and {@link #removeAll
 * removeAll} methods, which contain values that have just been removed from the multimap, are
 * naturally <i>not</i> views.
 *
 * <h3>Subinterfaces</h3>
 *
 * Instead of using the {@code Multimap} interface directly, prefer the subinterfaces {@link
 * ListMultimap} and {@link SetMultimap}. These take their names from the fact that the collections
 * they return from {@code get} behave like (and, of course, implement) {@link List} and {@link
 * Set}, respectively.
 *
 * For example, the "presidents" code snippet above used a {@code ListMultimap}; if it had used a
 * {@code SetMultimap} instead, two presidents would have vanished, and last names might or might
 * not appear in chronological order.
 *
 * <b>Warning:</b> instances of type {@code Multimap} may not implement {@link Object#equals} in
 * the way you expect. Multimaps containing the same key-value pairs, even in the same order, may or
 * may not be equal and may or may not have the same {@code hashCode}. The recommended subinterfaces
 * provide much stronger guarantees.
 *
 * <h3>Comparison to a map of collections</h3>
 *
 * Multimaps are commonly used in places where a {@code Map<K, Collection<V>>} would otherwise
 * have appeared. The differences include:
 *
 * <ul>
 *   <li>There is no need to populate an empty collection before adding an entry with {@link #put
 *       put}.
 *   <li>{@code get} never returns {@code null}, only an empty collection.
 *   <li>A key is contained in the multimap if and only if it maps to at least one value. Any
 *       operation that causes a key to have zero associated values has the effect of
 *       <i>removing</i> that key from the multimap.
 *   <li>The total entry count is available as {@link #size}.
 *   <li>Many complex operations become easier; for example, {@code
 *       Collections.min(multimap.values())} finds the smallest value across all keys.
 * </ul>
 *
 * <h3>Implementations</h3>
 *
 * As always, prefer the immutable implementations, {@link ImmutableListMultimap} and {@link
 * ImmutableSetMultimap}. General-purpose mutable implementations are listed above under "All Known
 * Implementing Classes". You can also create a <i>custom</i> multimap, backed by any {@code Map}
 * and {@link Collection} types, using the {@link Multimaps#newMultimap Multimaps.newMultimap}
 * family of methods. Finally, another popular way to obtain a multimap is using {@link
 * Multimaps#index Multimaps.index}. See the {@link Multimaps} class for these and other static
 * utilities related to multimaps.
 *
 * <h3>Other Notes</h3>
 *
 * As with {@code Map}, the behavior of a {@code Multimap} is not specified if key objects
 * already present in the multimap change in a manner that affects {@code equals} comparisons. Use
 * caution if mutable objects are used as keys in a {@code Multimap}.
 *
 * All methods that modify the multimap are optional. The view collections returned by the
 * multimap may or may not be modifiable. Any modification method that is not supported will throw
 * {@link UnsupportedOperationException}.
 *
 * See the Guava User Guide article on <a href=
 * "https://github.com/google/guava/wiki/NewCollectionTypesExplained#multimap"> {@code
 * Multimap}</a>.
 *
 * @author Jared Levy
 * @since 2.0
 */
@GwtCompatible
public interface Multimap<K, V> {
  // Query Operations

  /**
   * Returns the number of key-value pairs in this multimap.
   *
   * <b>Note:</b> this method does not return the number of <i>distinct keys</i> in the multimap,
   * which is given by {@code keySet().size()} or {@code asMap().size()}. See the opening section of
   * the {@link Multimap} class documentation for clarification.
   */
  int size();  //返回Multimap集合的key、value pair的数量

  /**
   * Returns {@code true} if this multimap contains no key-value pairs. Equivalent to {@code size()
   * == 0}, but can in some cases be more efficient.
   */
  boolean isEmpty();  //判断Multimap是否包含key、value pair

  /**
   * Returns {@code true} if this multimap contains at least one key-value pair with the key {@code
   * key}.
   */
  boolean containsKey(@CompatibleWith("K") @Nullable Object key); //判断Multimap中是否包含指定key的value值

  /**
   * Returns {@code true} if this multimap contains at least one key-value pair with the value
   * {@code value}.
   */
  boolean containsValue(@CompatibleWith("V") @Nullable Object value); //判断Multimap中是否包含指定value的key

  /**
   * Returns {@code true} if this multimap contains at least one key-value pair with the key {@code
   * key} and the value {@code value}.
   */
  boolean containsEntry(@CompatibleWith("K") @Nullable Object key, @CompatibleWith("V") @Nullable Object value); //判断Multimap中是否包含指定的key-value pair的数据

  // Modification Operations

  /**
   * Stores a key-value pair in this multimap.
   *
   * Some multimap implementations allow duplicate key-value pairs, in which case {@code put}
   * always adds a new key-value pair and increases the multimap size by 1. Other implementations
   * prohibit duplicates, and storing a key-value pair that's already in the multimap has no effect.
   *
   * @return {@code true} if the method increased the size of the multimap, or {@code false} if the
   *     multimap already contained the key-value pair and doesn't allow duplicates
   */
  @CanIgnoreReturnValue
  boolean put(@Nullable K key, @Nullable V value); //将数据加入到Multimap中

  /**
   * Removes a single key-value pair with the key {@code key} and the value {@code value} from this
   * multimap, if such exists. If multiple key-value pairs in the multimap fit this description,
   * which one is removed is unspecified.
   *
   * @return {@code true} if the multimap changed
   */
  @CanIgnoreReturnValue
  boolean remove( @CompatibleWith("K") @Nullable Object key, @CompatibleWith("V") @Nullable Object value);  //删除Multimap中指定key-value pair

  // Bulk Operations

  /**
   * Stores a key-value pair in this multimap for each of {@code values}, all using the same key,
   * {@code key}. Equivalent to (but expected to be more efficient than):
   *
   * <pre>{@code
   * for (V value : values) {
   *   put(key, value);
   * }
   * }</pre>
   *
   * In particular, this is a no-op if {@code values} is empty.
   *
   * @return {@code true} if the multimap changed
   */
  @CanIgnoreReturnValue
  boolean putAll(@Nullable K key, Iterable<? extends V> values); //将指定的key-集合数据加入Multimap中

  /**
   * Stores all key-value pairs of {@code multimap} in this multimap, in the order returned by
   * {@code multimap.entries()}.
   *
   * @return {@code true} if the multimap changed
   */
  @CanIgnoreReturnValue
  boolean putAll(Multimap<? extends K, ? extends V> multimap); //将指定的Multimap和当前的Multimap合并

  /**
   * Stores a collection of values with the same key, replacing any existing values for that key.
   *
   * If {@code values} is empty, this is equivalent to {@link #removeAll(Object) removeAll(key)}.
   *
   * @return the collection of replaced values, or an empty collection if no values were previously
   *     associated with the key. The collection <i>may</i> be modifiable, but updating it will have
   *     no effect on the multimap.
   */
  @CanIgnoreReturnValue
  Collection<V> replaceValues(@Nullable K key, Iterable<? extends V> values);//替换指定key的value

  /**
   * Removes all values associated with the key {@code key}.
   *
   * Once this method returns, {@code key} will not be mapped to any values, so it will not
   * appear in {@link #keySet()}, {@link #asMap()}, or any other views.
   *
   * @return the values that were removed (possibly empty). The returned collection <i>may</i> be
   *     modifiable, but updating it will have no effect on the multimap.
   */
  @CanIgnoreReturnValue
  Collection<V> removeAll(@CompatibleWith("K") @Nullable Object key); //删除Imultimap中的指定key数据

  /** Removes all key-value pairs from the multimap, leaving it {@linkplain #isEmpty empty}. */
  void clear(); //清空Imultimap中的数据

  // Views

  /**
   * Returns a view collection of the values associated with {@code key} in this multimap, if any.
   * Note that when {@code containsKey(key)} is false, this returns an empty collection, not {@code null}.
   *  Changes to the returned collection will update the underlying multimap, and vice versa.
   *  改变 从基础Map返回的视图操作  将同时映射到基础Map  反之亦然
   */
  Collection<V> get(@Nullable K key); //获取指定key的值

  /**
   * Returns a view collection of all <i>distinct</i> keys contained in this multimap. Note that the
   * key set contains a key if and only if this multimap maps that key to at least one value.
   *
   * Changes to the returned set will update the underlying multimap, and vice versa. However,
   * <i>adding</i> to the returned set is not possible.
   */
  Set<K> keySet(); //获取所有的key集合

  /**
   * Returns a view collection containing the key from each key-value pair in this multimap,
   * <i>without</i> collapsing duplicates. This collection has the same size as this multimap, and
   * {@code keys().count(k) == get(k).size()} for all {@code k}.
   *
   * Changes to the returned multiset will update the underlying multimap, and vice versa.
   * However, <i>adding</i> to the returned collection is not possible.
   */
  Multiset<K> keys();

  /**
   * Returns a view collection containing the <i>value</i> from each key-value pair contained in
   * this multimap, without collapsing duplicates (so {@code values().size() == size()}).
   *
   * Changes to the returned collection will update the underlying multimap, and vice versa.
   * However, <i>adding</i> to the returned collection is not possible.
   */
  Collection<V> values();

  /**
   * Returns a view collection of all key-value pairs contained in this multimap, as {@link Entry}
   * instances.
   *
   * Changes to the returned collection or the entries it contains will update the underlying
   * multimap, and vice versa. However, <i>adding</i> to the returned collection is not possible.
   */
  Collection<Entry<K, V>> entries();

  /**
   * Performs the given action for all key-value pairs contained in this multimap. If an ordering is
   * specified by the {@code Multimap} implementation, actions will be performed in the order of
   * iteration of {@link #entries()}. Exceptions thrown by the action are relayed to the caller.
   *
   * To loop over all keys and their associated value collections, write {@code
   * Multimaps.asMap(multimap).forEach((key, valueCollection) -> action())}.
   *
   * @since 21.0
   */
  default void forEach(BiConsumer<? super K, ? super V> action) {
    checkNotNull(action);
    entries().forEach(entry -> action.accept(entry.getKey(), entry.getValue()));
  }

  /**
   * Returns a view of this multimap as a {@code Map} from each distinct key to the nonempty
   * collection of that key's associated values. Note that {@code this.asMap().get(k)} is equivalent
   * to {@code this.get(k)} only when {@code k} is a key contained in the multimap; otherwise it
   * returns {@code null} as opposed to an empty collection.
   *
   * Changes to the returned map or the collections that serve as its values will update the
   * underlying multimap, and vice versa. The map does not support {@code put} or {@code putAll},
   * nor do its entries support {@link Entry#setValue setValue}.
   */
  Map<K, Collection<V>> asMap();

  // Comparison and hashing

  /**
   * Compares the specified object with this multimap for equality. Two multimaps are equal when
   * their map views, as returned by {@link #asMap}, are also equal.
   *
   * In general, two multimaps with identical key-value mappings may or may not be equal,
   * depending on the implementation. For example, two {@link SetMultimap} instances with the same
   * key-value mappings are equal, but equality of two {@link ListMultimap} instances depends on the
   * ordering of the values for each key.
   *
   * A non-empty {@link SetMultimap} cannot be equal to a non-empty {@link ListMultimap}, since
   * their {@link #asMap} views contain unequal collections as values. However, any two empty
   * multimaps are equal, because they both have empty {@link #asMap} views.
   */
  @Override
  boolean equals(@Nullable Object obj);

  /**
   * Returns the hash code for this multimap.
   *
   * The hash code of a multimap is defined as the hash code of the map view, as returned by
   * {@link Multimap#asMap}.
   *
   * In general, two multimaps with identical key-value mappings may or may not have the same
   * hash codes, depending on the implementation. For example, two {@link SetMultimap} instances
   * with the same key-value mappings will have the same {@code hashCode}, but the {@code hashCode}
   * of {@link ListMultimap} instances depends on the ordering of the values for each key.
   */
  @Override
  int hashCode();
}
