package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.ScheduledFuture;

/**
 * Helper interface to implement both {@link ListenableFuture} and {@link ScheduledFuture}.
 *
 * @author Anthony Zana
 * @since 15.0
 */
@Beta
@GwtCompatible
public interface ListenableScheduledFuture<V> extends ScheduledFuture<V>, ListenableFuture<V> {}
