package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors;

import java.lang.reflect.Field;

public class StoryAnnotationError extends Error {
    private final ErrType errType;
    private final Class annotation;
    private final Field annotatedField;

    public StoryAnnotationError(ErrType errType, Class annotation, Field annotatedField) {
        super(("Reason: %s; Invalid annotation \"%s\" found at \"%s\" field. " +
                "Annotations in Story are important, " +
                "running without processing them would cause many unfixable fails later")
                .formatted(errType.name(), annotation.getName(), annotatedField.getName()));
        this.annotation = annotation;
        this.annotatedField = annotatedField;
        this.errType = errType;
    }

    public Class getAnnotation() {
        return annotation;
    }

    public Field getAnnotatedField() {
        return annotatedField;
    }

    public ErrType getErrType() {
        return errType;
    }

    public enum ErrType {
        ABILITY_EXISTS,
        INVALID_TYPE
    }
}
