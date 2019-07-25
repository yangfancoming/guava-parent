package com.google.common.reflect;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.annotations.Beta;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/**
 * Represents a method or constructor parameter.
 *
 * @author Ben Yu
 * @since 14.0
 */
@Beta
public final class Parameter implements AnnotatedElement {

  private final Invokable<?, ?> declaration;
  private final int position;
  private final TypeToken<?> type;
  private final ImmutableList<Annotation> annotations;

  Parameter(
      Invokable<?, ?> declaration, int position, TypeToken<?> type, Annotation[] annotations) {
    this.declaration = declaration;
    this.position = position;
    this.type = type;
    this.annotations = ImmutableList.copyOf(annotations);
  }

  /** Returns the type of the parameter. */
  public TypeToken<?> getType() {
    return type;
  }

  /** Returns the {@link Invokable} that declares this parameter. */
  public Invokable<?, ?> getDeclaringInvokable() {
    return declaration;
  }

  @Override
  public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
    return getAnnotation(annotationType) != null;
  }

  @Override
  @NullableDecl
  public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
    checkNotNull(annotationType);
    for (Annotation annotation : annotations) {
      if (annotationType.isInstance(annotation)) {
        return annotationType.cast(annotation);
      }
    }
    return null;
  }

  @Override
  public Annotation[] getAnnotations() {
    return getDeclaredAnnotations();
  }

  /** @since 18.0 */
  // @Override on JDK8
  public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationType) {
    return getDeclaredAnnotationsByType(annotationType);
  }

  /** @since 18.0 */
  // @Override on JDK8
  @Override
  public Annotation[] getDeclaredAnnotations() {
    return annotations.toArray(new Annotation[annotations.size()]);
  }

  /** @since 18.0 */
  // @Override on JDK8
  @NullableDecl
  public <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationType) {
    checkNotNull(annotationType);
    return FluentIterable.from(annotations).filter(annotationType).first().orNull();
  }

  /** @since 18.0 */
  // @Override on JDK8
  public <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationType) {
    return FluentIterable.from(annotations).filter(annotationType).toArray(annotationType);
  }

  @Override
  public boolean equals(@NullableDecl Object obj) {
    if (obj instanceof Parameter) {
      Parameter that = (Parameter) obj;
      return position == that.position && declaration.equals(that.declaration);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return position;
  }

  @Override
  public String toString() {
    return type + " arg" + position;
  }
}
