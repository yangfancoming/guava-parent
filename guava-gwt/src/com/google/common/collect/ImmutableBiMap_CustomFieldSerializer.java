package com.google.common.collect;

/**
 * Even though {@link ImmutableBiMap} cannot be instantiated, we still need a custom field
 * serializer to unify the type signature of {@code ImmutableBiMap[]} on server and client side.
 *
 * @author Hayward Chan
 */
public final class ImmutableBiMap_CustomFieldSerializer {}
