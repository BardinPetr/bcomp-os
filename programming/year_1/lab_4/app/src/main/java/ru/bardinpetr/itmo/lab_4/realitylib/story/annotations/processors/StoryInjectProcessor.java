package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors;

import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryInject;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors.StoryAnnotationError;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors.StoryDIError;

import java.lang.reflect.Field;

public class StoryInjectProcessor {

    public static void process(Story main, Story target) {
        for (Field field : target.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(StoryInject.class)) continue;

            field.setAccessible(true);

            Object data = main.getProvidedObject(field.getName(), field.getType());
            if(data == null) {
                throw new StoryDIError(StoryInject.class, field, "Cannot find provider for your request");
            }

            try {
                field.set(target, data);
            } catch (IllegalAccessException e) {
                throw new StoryAnnotationError(
                        StoryAnnotationError.ErrType.INVALID_TYPE,
                        StoryInject.class,
                        field);
            }
        }
    }
}
