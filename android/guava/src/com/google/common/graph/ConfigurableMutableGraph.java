

package com.google.common.graph;

import com.google.common.graph.GraphConstants.Presence;

/**
 * Configurable implementation of {@link MutableGraph} that supports both directed and undirected
 * graphs. Instances of this class should be constructed with {@link GraphBuilder}.
 *
 * <p>Time complexities for mutation methods are all O(1) except for {@code removeNode(N node)},
 * which is in O(d_node) where d_node is the degree of {@code node}.
 *
 * @author James Sexton
 * @param <N> Node parameter type
 */
final class ConfigurableMutableGraph<N> extends ForwardingGraph<N> implements MutableGraph<N> {
  private final MutableValueGraph<N, Presence> backingValueGraph;

  /** Constructs a {@link MutableGraph} with the properties specified in {@code builder}. */
  ConfigurableMutableGraph(AbstractGraphBuilder<? super N> builder) {
    this.backingValueGraph = new ConfigurableMutableValueGraph<>(builder);
  }

  @Override
  protected BaseGraph<N> delegate() {
    return backingValueGraph;
  }

  @Override
  public boolean addNode(N node) {
    return backingValueGraph.addNode(node);
  }

  @Override
  public boolean putEdge(N nodeU, N nodeV) {
    return backingValueGraph.putEdgeValue(nodeU, nodeV, Presence.EDGE_EXISTS) == null;
  }

  @Override
  public boolean putEdge(EndpointPair<N> endpoints) {
    validateEndpoints(endpoints);
    return putEdge(endpoints.nodeU(), endpoints.nodeV());
  }

  @Override
  public boolean removeNode(N node) {
    return backingValueGraph.removeNode(node);
  }

  @Override
  public boolean removeEdge(N nodeU, N nodeV) {
    return backingValueGraph.removeEdge(nodeU, nodeV) != null;
  }

  @Override
  public boolean removeEdge(EndpointPair<N> endpoints) {
    validateEndpoints(endpoints);
    return removeEdge(endpoints.nodeU(), endpoints.nodeV());
  }
}
