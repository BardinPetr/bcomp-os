package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StoryInvocationError extends Error {
    private final InvocationTargetException exception;

    public StoryInvocationError(InvocationTargetException exception) {
        this.exception = exception;
    }

    public InvocationTargetException getException() {
        return exception;
    }
}
