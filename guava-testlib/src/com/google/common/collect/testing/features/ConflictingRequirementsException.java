

package com.google.common.collect.testing.features;

import com.google.common.annotations.GwtCompatible;
import java.util.Set;

/**
 * Thrown when requirements on a tester method or class conflict with each other.
 *
 * @author George van den Driessche
 */
@GwtCompatible
public class ConflictingRequirementsException extends Exception {
  private Set<Feature<?>> conflicts;
  private Object source;

  public ConflictingRequirementsException(
      String message, Set<Feature<?>> conflicts, Object source) {
    super(message);
    this.conflicts = conflicts;
    this.source = source;
  }

  public Set<Feature<?>> getConflicts() {
    return conflicts;
  }

  public Object getSource() {
    return source;
  }

  @Override
  public String getMessage() {
    return super.getMessage() + " (source: " + source + ")";
  }

  private static final long serialVersionUID = 0;
}
