package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.abilities;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AbilityContainer.class)
public @interface Able {
    Class value();
}
