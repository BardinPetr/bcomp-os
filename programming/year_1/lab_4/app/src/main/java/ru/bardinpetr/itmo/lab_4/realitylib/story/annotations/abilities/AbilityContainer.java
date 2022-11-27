package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.abilities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AbilityContainer {
    Able[] value();
}
