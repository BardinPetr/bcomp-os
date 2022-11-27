package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors;

import java.lang.reflect.Field;

public class StoryDIError extends Error {

    private final Class annotation;
    private final Field field;
    private final String diMessage;

    public StoryDIError(Class annotation, Field field, String diMessage) {
        this.annotation = annotation;
        this.field = field;
        this.diMessage = diMessage;
    }

    public Class getAnnotation() {
        return annotation;
    }

    public Field getField() {
        return field;
    }

    public String getDiMessage() {
        return diMessage;
    }
}
