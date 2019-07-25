package com.google.common.collect;

/**
 * Even though {@link ImmutableSet} cannot be instantiated, we still need a custom field serializer
 * to unify the type signature of {@code ImmutableSet[]} on server and client side.
 *
 * @author Hayward Chan
 */
public final class ImmutableSet_CustomFieldSerializer {}
