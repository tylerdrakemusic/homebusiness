package com.vt.fish.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VibrantLog {
    String vibrantTropicalRequestId () default "";
    String correlationId () default "";
    String before() default "";
    String after() default  "";
    //todo: add level
}
