package com.google.common.collect;

/**
 * Even though {@link ImmutableMultiset} cannot be instantiated, we still need a custom field
 * serializer to unify the type signature of {@code ImmutableMultiset[]} on server and client side.
 *
 * @author Chris Povirk
 */
public class ImmutableMultiset_CustomFieldSerializer {}
