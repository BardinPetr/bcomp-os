package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AbilityContainer.class)
public @interface Able {
    Class value();
}
