

package com.google.common.collect;

import static java.util.Arrays.asList;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.google.MultisetFeature;
import com.google.common.collect.testing.google.MultisetTestSuiteBuilder;
import com.google.common.collect.testing.google.TestStringMultisetGenerator;
import com.google.common.testing.SerializableTester;
import java.io.Serializable;
import java.util.Arrays;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for {@link HashMultiset}.
 *
 * @author Kevin Bourrillion
 * @author Jared Levy
 */
@GwtCompatible(emulated = true)
public class HashMultisetTest extends TestCase {

  @GwtIncompatible // suite
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(
        MultisetTestSuiteBuilder.using(hashMultisetGenerator())
            .withFeatures(
                CollectionSize.ANY,
                CollectionFeature.FAILS_FAST_ON_CONCURRENT_MODIFICATION,
                CollectionFeature.ALLOWS_NULL_VALUES,
                CollectionFeature.SERIALIZABLE,
                CollectionFeature.GENERAL_PURPOSE,
                MultisetFeature.ENTRIES_ARE_VIEWS)
            .named("HashMultiset")
            .createTestSuite());
    suite.addTestSuite(HashMultisetTest.class);
    return suite;
  }

  private static TestStringMultisetGenerator hashMultisetGenerator() {
    return new TestStringMultisetGenerator() {
      @Override
      protected Multiset<String> create(String[] elements) {
        return HashMultiset.create(asList(elements));
      }
    };
  }

  public void testCreate() {
    Multiset<String> multiset = HashMultiset.create();
    multiset.add("foo", 2);
    multiset.add("bar");
    assertEquals(3, multiset.size());
    assertEquals(2, multiset.count("foo"));
  }

  public void testCreateWithSize() {
    Multiset<String> multiset = HashMultiset.create(50);
    multiset.add("foo", 2);
    multiset.add("bar");
    assertEquals(3, multiset.size());
    assertEquals(2, multiset.count("foo"));
  }

  public void testCreateFromIterable() {
    Multiset<String> multiset = HashMultiset.create(Arrays.asList("foo", "bar", "foo"));
    assertEquals(3, multiset.size());
    assertEquals(2, multiset.count("foo"));
  }

  @GwtIncompatible // SerializableTester
  public void testSerializationContainingSelf() {
    Multiset<Multiset<?>> multiset = HashMultiset.create();
    multiset.add(multiset, 2);
    Multiset<Multiset<?>> copy = SerializableTester.reserialize(multiset);
    assertEquals(2, copy.size());
    assertSame(copy, copy.iterator().next());
  }

  @GwtIncompatible // Only used by @GwtIncompatible code
  private static class MultisetHolder implements Serializable {
    public Multiset<?> member;

    MultisetHolder(Multiset<?> multiset) {
      this.member = multiset;
    }

    private static final long serialVersionUID = 1L;
  }

  @GwtIncompatible // SerializableTester
  public void testSerializationIndirectSelfReference() {
    Multiset<MultisetHolder> multiset = HashMultiset.create();
    MultisetHolder holder = new MultisetHolder(multiset);
    multiset.add(holder, 2);
    Multiset<MultisetHolder> copy = SerializableTester.reserialize(multiset);
    assertEquals(2, copy.size());
    assertSame(copy, copy.iterator().next().member);
  }

  /*
   * The behavior of toString() and iteration is tested by LinkedHashMultiset,
   * which shares a lot of code with HashMultiset and has deterministic
   * iteration order.
   */
}
