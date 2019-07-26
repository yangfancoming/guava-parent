

package com.google.common.collect.testing;

import com.google.common.annotations.GwtCompatible;
import java.util.Iterator;

/**
 * Adapts a test iterable generator to give a TestIteratorGenerator.
 *
 * @author George van den Driessche
 */
@GwtCompatible
public final class DerivedTestIteratorGenerator<E>
    implements TestIteratorGenerator<E>, DerivedGenerator {
  private final TestSubjectGenerator<? extends Iterable<E>> collectionGenerator;

  public DerivedTestIteratorGenerator(
      TestSubjectGenerator<? extends Iterable<E>> collectionGenerator) {
    this.collectionGenerator = collectionGenerator;
  }

  @Override
  public TestSubjectGenerator<? extends Iterable<E>> getInnerGenerator() {
    return collectionGenerator;
  }

  @Override
  public Iterator<E> get() {
    return collectionGenerator.createTestSubject().iterator();
  }
}
