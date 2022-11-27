package ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.errors.AbilityExistsException;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.IAble;
import ru.bardinpetr.itmo.lab_4.realitylib.story.SubStory;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.AbilityContainer;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.Able;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.errors.StoryAnnotationError;

public class AbleProcessor {
    public static void process(SubStory story) {
        var sClass = story.getClass();
        for (var field : sClass.getDeclaredFields()) {
            var container = field.getAnnotation(AbilityContainer.class);
            var single = field.getAnnotation(Able.class);

            if (container == null && single == null) continue;
            field.setAccessible(true);

            IAble executor;
            try {
                executor = (IAble) field.get(story);
            } catch (ClassCastException e) {
                throw new StoryAnnotationError(StoryAnnotationError.ErrType.INVALID_TYPE, Able.class, field);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            try {
                if (container != null) {
                    for (Able annot : container.value())
                        executor.addAbility(annot.value());
                } else {
                    executor.addAbility(single.value());
                }
            } catch (AbilityExistsException ignore) {
//                throw new StoryAnnotationError(StoryAnnotationError.ErrType.ABILITY_EXISTS, Able.class, field);
            }
        }
    }
}