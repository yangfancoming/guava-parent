

package com.google.common.collect;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth8.assertThat;

import com.google.common.annotations.GwtCompatible;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import junit.framework.TestCase;

/**
 * Tests for {@code MoreCollectors}.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
public class MoreCollectorsTest extends TestCase {
  public void testToOptionalEmpty() {
    assertThat(Stream.empty().collect(MoreCollectors.toOptional())).isEmpty();
  }

  public void testToOptionalSingleton() {
    assertThat(Stream.of(1).collect(MoreCollectors.toOptional())).hasValue(1);
  }

  public void testToOptionalNull() {
    Stream<Object> stream = Stream.of((Object) null);
    try {
      stream.collect(MoreCollectors.toOptional());
      fail("Expected NullPointerException");
    } catch (NullPointerException expected) {
    }
  }

  public void testToOptionalMultiple() {
    try {
      Stream.of(1, 2).collect(MoreCollectors.toOptional());
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("1, 2");
    }
  }

  public void testToOptionalMany() {
    try {
      Stream.of(1, 2, 3, 4, 5, 6).collect(MoreCollectors.toOptional());
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("1, 2, 3, 4, 5, ...");
    }
  }

  public void testOnlyElement() {
    try {
      Stream.empty().collect(MoreCollectors.onlyElement());
      fail("Expected NoSuchElementException");
    } catch (NoSuchElementException expected) {
    }
  }

  public void testOnlyElementSingleton() {
    assertThat(Stream.of(1).collect(MoreCollectors.onlyElement())).isEqualTo(1);
  }

  public void testOnlyElementNull() {
    assertThat(Stream.of((Object) null).collect(MoreCollectors.onlyElement())).isNull();
  }

  public void testOnlyElementMultiple() {
    try {
      Stream.of(1, 2).collect(MoreCollectors.onlyElement());
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("1, 2");
    }
  }

  public void testOnlyElementMany() {
    try {
      Stream.of(1, 2, 3, 4, 5, 6).collect(MoreCollectors.onlyElement());
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException expected) {
      assertThat(expected.getMessage()).contains("1, 2, 3, 4, 5, ...");
    }
  }
}
