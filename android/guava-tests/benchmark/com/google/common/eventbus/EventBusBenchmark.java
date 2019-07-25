

package com.google.common.eventbus;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;

/**
 * Benchmark for {@link EventBus}.
 *
 * @author Eric Fellheimer
 */
public class EventBusBenchmark {

  private EventBus eventBus;

  @BeforeExperiment
  void setUp() {
    eventBus = new EventBus("for benchmarking purposes");
    eventBus.register(this);
  }

  @Benchmark
  void postStrings(int reps) {
    for (int i = 0; i < reps; i++) {
      eventBus.post("hello there");
    }
  }

  @Subscribe
  public void handleStrings(String string) {
    // Nothing to do here.
  }
}
