package com.google.common.collect.testing.testers;

import static com.google.common.collect.testing.features.CollectionFeature.SERIALIZABLE;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.testing.AbstractMapTester;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.testing.EqualsTester;
import com.google.common.testing.SerializableTester;
import java.util.Map;
import org.junit.Ignore;

/**
 * Basic serialization test for maps.
 *
 * @author Louis Wasserman
 */
@GwtCompatible
@Ignore // Affects only Android test runner, which respects JUnit 4 annotations on JUnit 3 tests.
public class MapSerializationTester<K, V> extends AbstractMapTester<K, V> {
  @CollectionFeature.Require(SERIALIZABLE)
  public void testReserializeMap() {
    Map<K, V> deserialized = SerializableTester.reserialize(getMap());
    new EqualsTester().addEqualityGroup(getMap(), deserialized).testEquals();
  }
}
