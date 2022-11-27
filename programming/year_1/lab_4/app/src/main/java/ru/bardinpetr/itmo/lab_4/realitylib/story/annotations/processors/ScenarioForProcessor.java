package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors;

import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.Scriptable;
import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.ScenarioFor;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors.StoryInvocationError;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors.StoryScenarioAnnotationError;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ScenarioForProcessor {
    public static void process(Story story) {
        var sClass = story.getClass();
        for (var method : sClass.getDeclaredMethods()) {

            var annotation = method.getAnnotation(ScenarioFor.class);

            if (annotation == null ||
                    method.getParameterCount() != 0 ||
                    method.getReturnType() != Scenario[].class) continue;

            method.setAccessible(true);

            Scenario[] scenarios;
            try {
                scenarios = (Scenario[]) method.invoke(story, new Object[]{});
            } catch (IllegalAccessException | ClassCastException e) {
                throw new StoryScenarioAnnotationError(method);
            } catch (InvocationTargetException e) {
                throw new StoryInvocationError(e);
            }

            try {
                Field targetField = sClass.getDeclaredField(annotation.value());
                targetField.setAccessible(true);
                Scriptable actor = (Scriptable) targetField.get(story);
                for(var s: scenarios) actor.addScenario(s);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new StoryScenarioAnnotationError(method, "No such actor");
            }
        }
    }
}