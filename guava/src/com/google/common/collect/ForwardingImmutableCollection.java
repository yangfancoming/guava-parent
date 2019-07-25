

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/**
 * Dummy class that makes the GWT serialization policy happy. It isn't used on the server-side.
 *
 * @author Hayward Chan
 */
@GwtCompatible(emulated = true)
class ForwardingImmutableCollection {
  private ForwardingImmutableCollection() {}
}
