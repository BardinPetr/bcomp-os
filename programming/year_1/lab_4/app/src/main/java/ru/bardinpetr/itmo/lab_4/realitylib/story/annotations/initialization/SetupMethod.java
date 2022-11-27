package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.initialization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows to separate setup of environment/actors/other via methods.
 * Method annotate with this will be called after DI before setup of scenarios
 * Should have signature void someMethod()
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SetupMethod {
}
