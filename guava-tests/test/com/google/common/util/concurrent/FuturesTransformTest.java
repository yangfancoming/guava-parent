

package com.google.common.util.concurrent;

import static com.google.common.util.concurrent.Futures.transform;
import static com.google.common.util.concurrent.MoreExecutors.directExecutor;

import com.google.common.base.Function;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Unit tests for {@link Futures#transform(ListenableFuture, Function, Executor)}.
 *
 * @author Nishant Thakkar
 */
public class FuturesTransformTest extends AbstractChainedListenableFutureTest<String> {
  private static final String RESULT_DATA = "SUCCESS";
  private static final UndeclaredThrowableException WRAPPED_EXCEPTION =
      new UndeclaredThrowableException(EXCEPTION);

  @Override
  protected ListenableFuture<String> buildChainingFuture(ListenableFuture<Integer> inputFuture) {
    return transform(inputFuture, new ComposeFunction(), directExecutor());
  }

  @Override
  protected String getSuccessfulResult() {
    return RESULT_DATA;
  }

  private class ComposeFunction implements Function<Integer, String> {
    @Override
    public String apply(Integer input) {
      if (input.intValue() == VALID_INPUT_DATA) {
        return RESULT_DATA;
      } else {
        throw WRAPPED_EXCEPTION;
      }
    }
  }

  public void testFutureGetThrowsFunctionException() throws Exception {
    inputFuture.set(EXCEPTION_DATA);
    listener.assertException(WRAPPED_EXCEPTION);
  }
}
