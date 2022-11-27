package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors;

import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.initialization.CreateScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors.StoryScenarioAnnotationError;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class CreateScenarioProcessor {
    public static void process(Story target) {
        var targetClass = target.getClass();
        for (var method : targetClass.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(CreateScenario.class))
                continue;

            if (method.getReturnType() != void.class || !Arrays.equals(method.getParameterTypes(), new Class[]{Scenario.class}))
                continue;

            method.setAccessible(true);
            String name = method.getName();

            Scenario data;
            try {
                Field field = targetClass.getDeclaredField(name);
                field.setAccessible(true);
                data = (Scenario) field.get(target);
            } catch (ClassCastException | NoSuchFieldException | IllegalAccessException e) {
                throw new StoryScenarioAnnotationError(method, "Could not get field corresponding scenario method");
            }

            try {
                method.invoke(target, data);
            } catch (IllegalAccessException e) {
                throw new StoryScenarioAnnotationError(method, "Invalid scenario method");
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}