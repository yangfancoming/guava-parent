package com.google.common.collect;

/**
 * Even though {@link ImmutableList} cannot be instantiated, we still need a custom field serializer
 * to unify the type signature of {@code ImmutableList[]} on server and client side.
 *
 * @author Hayward Chan
 */
public final class ImmutableList_CustomFieldSerializer {}
