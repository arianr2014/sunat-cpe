package io.github.carlosthe19916.core.models.graph;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Use a Java class to handle frame method calls. Unless overridden using the
 * {@link JavaHandler } annotation the default handler will be a nested
 * class inside your frame interface called Impl. For example:
 * </p>
 * <pre>
 *
 * interface Person {
 *
 *   &#064;JavaHandler
 *   public String doSomething();
 *
 *   abstract class Impl implements Person, JavaHandlerContext {
 *     public String doSomething() {
 *       return "Use Frames!";
 *     }
 *   }
 * }
 * </pre>
 *
 * (Inspired directly from the Tinkerpop Frames implementation)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JavaHandler
{
    Class<?> handler();
}
