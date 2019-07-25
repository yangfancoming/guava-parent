package com.google.common.collect;

/**
 * Even though {@link ImmutableSortedSet} cannot be instantiated, we still need a custom field
 * serializer to unify the type signature of {@code ImmutableSortedSet[]} on server and client side.
 *
 * @author Hayward Chan
 */
public final class ImmutableSortedSet_CustomFieldSerializer {}
