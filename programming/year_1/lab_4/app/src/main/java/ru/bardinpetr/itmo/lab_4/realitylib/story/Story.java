package ru.bardinpetr.itmo.lab_4.realitylib.story;

import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors.AbleProcessor;
import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;

import java.util.ArrayList;
import java.util.List;

public class Story {
    private final List<Human> actors = new ArrayList<>();
    private final List<Thing> environment = new ArrayList<>();

    public Story() {
    }

    public static void compile(Story story) {
        AbleProcessor.process(story);
    }

}
