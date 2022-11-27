package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors;

import java.lang.reflect.Method;

public class StoryScenarioAnnotationError extends Error {
    private final Method annotatedMethod;

    public StoryScenarioAnnotationError(Method annotatedMethod) {
        this(annotatedMethod, "");
    }

    public StoryScenarioAnnotationError(Method annotatedMethod, String info) {
        super(("Failed to process \"%s\" scenario generator with @ScenarioFor annotation %s. " +
                "Annotations in Story are important, " +
                "running without processing them would cause many unfixable fails later")
                .formatted(info, annotatedMethod.getName()));
        this.annotatedMethod = annotatedMethod;
    }

    public Method getAnnotatedMethod() {
        return annotatedMethod;
    }
}
