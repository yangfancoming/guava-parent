package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;

/**
 * A generator that relies on a preexisting generator for most of its work. For example, a derived
 * iterator generator may delegate the work of creating the underlying collection to an inner
 * collection generator.
 *
 * <p>{@code GwtTestSuiteGenerator} expects every {@code DerivedIterator} implementation to provide
 * a one-arg constructor accepting its inner generator as an argument). This requirement enables it
 * to generate source code (since GWT cannot use reflection to generate the suites).
 *
 * @author Chris Povirk
 */
@GwtCompatible
public interface DerivedGenerator {
  TestSubjectGenerator<?> getInnerGenerator();
}
