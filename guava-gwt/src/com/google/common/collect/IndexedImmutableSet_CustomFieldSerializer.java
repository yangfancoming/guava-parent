

package com.google.common.collect;

/**
 * Dummy serializer. Otherwise, GWT, in processing JdkBackedImmutableSet -- even though that class
 * has a custom field serializer -- would generate its own version of this class, implemented in
 * terms of calls to ImmutableSet_CustomFieldSerializer, which is itself a dummy that we've
 * provided. That produces GWT compilation errors, albeit ones that are non-fatal (even with -strict
 * on, oddly).
 */
public final class IndexedImmutableSet_CustomFieldSerializer {}
