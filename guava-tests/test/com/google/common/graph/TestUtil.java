

package com.google.common.graph;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;

/** Utility methods used in various common.graph tests. */
final class TestUtil {
  static final String ERROR_ELEMENT_NOT_IN_GRAPH = "not an element of this graph";
  static final String ERROR_NODE_NOT_IN_GRAPH =
      "Should not be allowed to pass a node that is not an element of the graph.";
  private static final String NODE_STRING = "Node";
  private static final String EDGE_STRING = "Edge";

  enum EdgeType {
    UNDIRECTED,
    DIRECTED;
  }

  private TestUtil() {}

  static void assertNodeNotInGraphErrorMessage(Throwable throwable) {
    assertThat(throwable).hasMessageThat().startsWith(NODE_STRING);
    assertThat(throwable).hasMessageThat().contains(ERROR_ELEMENT_NOT_IN_GRAPH);
  }

  static void assertEdgeNotInGraphErrorMessage(Throwable throwable) {
    assertThat(throwable).hasMessageThat().startsWith(EDGE_STRING);
    assertThat(throwable).hasMessageThat().contains(ERROR_ELEMENT_NOT_IN_GRAPH);
  }

  static void assertStronglyEquivalent(Graph<?> graphA, Graph<?> graphB) {
    // Properties not covered by equals()
    assertThat(graphA.allowsSelfLoops()).isEqualTo(graphB.allowsSelfLoops());
    assertThat(graphA.nodeOrder()).isEqualTo(graphB.nodeOrder());

    assertThat(graphA).isEqualTo(graphB);
  }

  static void assertStronglyEquivalent(ValueGraph<?, ?> graphA, ValueGraph<?, ?> graphB) {
    // Properties not covered by equals()
    assertThat(graphA.allowsSelfLoops()).isEqualTo(graphB.allowsSelfLoops());
    assertThat(graphA.nodeOrder()).isEqualTo(graphB.nodeOrder());

    assertThat(graphA).isEqualTo(graphB);
  }

  static void assertStronglyEquivalent(Network<?, ?> networkA, Network<?, ?> networkB) {
    // Properties not covered by equals()
    assertThat(networkA.allowsParallelEdges()).isEqualTo(networkB.allowsParallelEdges());
    assertThat(networkA.allowsSelfLoops()).isEqualTo(networkB.allowsSelfLoops());
    assertThat(networkA.nodeOrder()).isEqualTo(networkB.nodeOrder());
    assertThat(networkA.edgeOrder()).isEqualTo(networkB.edgeOrder());

    assertThat(networkA).isEqualTo(networkB);
  }

  /**
   * In some cases our graph implementations return custom sets that define their own size() and
   * contains(). Verify that these sets are consistent with the elements of their iterator.
   */
  @CanIgnoreReturnValue
  static <T> Set<T> sanityCheckSet(Set<T> set) {
    assertThat(set).hasSize(Iterators.size(set.iterator()));
    for (Object element : set) {
      assertThat(set).contains(element);
    }
    assertThat(set).doesNotContain(new Object());
    assertThat(set).isEqualTo(ImmutableSet.copyOf(set));
    return set;
  }
}
