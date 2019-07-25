

package com.google.common.collect;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.google.common.annotations.GwtCompatible;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Private replacement for {@link com.google.gwt.user.client.rpc.GwtTransient} to work around
 * build-system quirks. This annotation should be used <b>only</b> in {@code
 * com.google.common.collect}.
 */
@Documented
@GwtCompatible
@Retention(RUNTIME)
@Target(FIELD)
@interface GwtTransient {}
