package ru.bardinpetr.itmo.lab_4.story.stories;

import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryProvide;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;
import ru.bardinpetr.itmo.lab_4.story.things.places.House;

public class MainStory extends Story {

    @StoryProvide
    private final HumanGroup littleManGroup = new HumanGroup("коротышки");

    @StoryProvide
    private final Place home = new House("домик", new double[]{1.2, 1.5}, 100);

    @StoryProvide
    private final Story forestStory = new ForestStory();

    public MainStory() {
        super("MAIN");
    }
}
