package ru.bardinpetr.itmo.lab_4.realitylib.story;

import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;

import java.util.ArrayList;
import java.util.List;

public class Story {
    private final List<Human> actors;
    private final List<Thing> environment;

    public List<Human> getActors() {
        return actors;
    }

    public List<Thing> getEnvironment() {
        return environment;
    }

    private Story(List<Human> actors, List<Thing> environment) {
        this.actors = actors;
        this.environment = environment;
    }

    public Story() {
        actors = new ArrayList<>();
        environment = new ArrayList<>();
        compile();
    }

    public void compile() {

    }

    public static class StoryBuilder {

        private final List<Human> actors = new ArrayList<>();
        private final List<Thing> environment = new ArrayList<>();

        private final List<Place> places = new ArrayList<>();

        public StoryBuilder addActor(Human actor) {
            actors.add(actor);
            return this;
        }

        public StoryBuilder addEnvironment(Place part) {
            places.add(part);
            return this;
        }

        public Story build() {
            return new Story(actors, environment);
        }
    }
}
