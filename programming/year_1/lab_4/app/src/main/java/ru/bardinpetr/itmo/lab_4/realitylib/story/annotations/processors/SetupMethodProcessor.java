package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors;

import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors.StoryScenarioAnnotationError;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.initialization.SetupMethod;

import java.lang.reflect.InvocationTargetException;

public class SetupMethodProcessor {
    public static void process(Story target) {
        var targetClass = target.getClass();
        for (var method : targetClass.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(SetupMethod.class))
                continue;

            if (method.getReturnType() != void.class || method.getParameterCount() != 0)
                continue;

            method.setAccessible(true);

            try {
                method.invoke(target);
            } catch (IllegalAccessException e) {
                throw new StoryScenarioAnnotationError(method, "Invalid setup method");
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}