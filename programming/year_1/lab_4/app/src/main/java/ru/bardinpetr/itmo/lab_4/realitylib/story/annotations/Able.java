package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AbilityContainer.class)
public @interface Able {
    Class value();
}
