package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Automates creation of Scenarios.
 * You should have a non-final Scenario field and same-named method of signature: void nameOfField(Scenario).
 * Method should contain code to setup contents of scenario.
 * The field will be initialized before DI started and the method will be called after DI ended
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CreateScenario {
}
