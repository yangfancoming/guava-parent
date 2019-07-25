

package com.google.common.reflect;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.annotations.Beta;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * Captures a free type variable that can be used in {@link TypeToken#where}. For example:
 *
 * <pre>{@code
 * static <T> TypeToken<List<T>> listOf(Class<T> elementType) {
 *   return new TypeToken<List<T>>() {}
 *       .where(new TypeParameter<T>() {}, elementType);
 * }
 * }</pre>
 *
 * @author Ben Yu
 * @since 12.0
 */
@Beta
public abstract class TypeParameter<T> extends TypeCapture<T> {

  final TypeVariable<?> typeVariable;

  protected TypeParameter() {
    Type type = capture();
    checkArgument(type instanceof TypeVariable, "%s should be a type variable.", type);
    this.typeVariable = (TypeVariable<?>) type;
  }

  @Override
  public final int hashCode() {
    return typeVariable.hashCode();
  }

  @Override
  public final boolean equals(@NullableDecl Object o) {
    if (o instanceof TypeParameter) {
      TypeParameter<?> that = (TypeParameter<?>) o;
      return typeVariable.equals(that.typeVariable);
    }
    return false;
  }

  @Override
  public String toString() {
    return typeVariable.toString();
  }
}
