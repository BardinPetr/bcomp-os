package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors;

import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryProvide;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors.StoryAnnotationError;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors.StoryDIError;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

import java.lang.reflect.Field;

public class StoryItemProviderProcessor {

    public static void process(Story main, Story sub) {
        Class subClass = sub.getClass();

        for (Field field : subClass.getDeclaredFields()) {
            StoryProvide annotation = field.getDeclaredAnnotation(StoryProvide.class);
            if (annotation == null) continue;

            String fieldName = field.getName();

            field.setAccessible(true);
            Object data;
            try {
                data = field.get(sub);
            } catch (IllegalAccessException e) {
                throw new StoryAnnotationError(
                        StoryAnnotationError.ErrType.INVALID_TYPE,
                        StoryProvide.class,
                        field);
            }

            Class target = field.getType();
            if (Human.class.isAssignableFrom(target)) {
                main.addActor(fieldName, (Human) data);
            } else if (HumanGroup.class.isAssignableFrom(target)) {
                main.addGroup(fieldName, (HumanGroup) data);
            } else if (Scenario.class.isAssignableFrom(target)) {
                main.addScenario(fieldName, (Scenario) data);
            } else if (Story.class == target) {
                main.addSubStory(fieldName, (Story) data);
            } else if (PhysicalObject.class.isAssignableFrom(target)) {
                main.addEnvironment(fieldName, (PhysicalObject) data);
            } else {
                throw new StoryDIError(StoryProvide.class, field, "You annotated not supported field type");
            }
        }
    }
}
