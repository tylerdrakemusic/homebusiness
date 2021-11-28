package com.vt.fish.logging.annotation;

import com.vt.fish.config.LoggingConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({LoggingConfiguration.class})
@Documented
public @interface EnableVibrantLogging {
}
