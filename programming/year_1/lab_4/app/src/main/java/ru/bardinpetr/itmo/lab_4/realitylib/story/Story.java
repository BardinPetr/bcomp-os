package ru.bardinpetr.itmo.lab_4.realitylib.story;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors.*;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is a base class for building a story.
 * Any story part should extend this class. Any sub-story should be a child of Main story.
 * It is intended not to interact with its internal structure.
 * The right way is to properly dislocate all dependencies and annotate your class:
 * All reused things should be in class's fields.
 * Any field that is used by at least two stories should be injected from parent story using DI.
 * When you create some field with object you want to include in the scene, annotate it with @StoryProvide
 * To inject field use @StoryInject. It will match by field name.
 * Types in DI are matched automatically by checking cast availability.
 * To add some code to set up environment (like mods) create any method and annotate it with @SetupMethod
 * To add scenario, create field for it, initialize with scenario object and create method annotated with @CreateScenario
 * To add pure abilities to IAble objects use @Able annotation.
 * To include a sub-story put it into fields, initialize and annotate as @StoryProvides
 * Startup sequence is the following:
 * 1. the main class is called with .compile()
 * 2. all @StoryProvides in this class is evaluated and store in Maps in Story
 * 2.1. StoryProvides also connects substories to current
 * 2.2. The main story loads all providers of sub
 * 2.3. The injects of sub is fulfilled from main
 * 2.4. substory is compiled (this list is a compile process)
 * 3. @Able annotations is processed for current class
 * 4. All setup methods of current class are called sequentially
 * 5. All scenario methods of current class are called sequentially
 * 6. The End!
 */
public abstract class Story implements Describable {
    private final String storyName;
    private final Map<String, Human> actors = new HashMap<>();
    private final Map<String, HumanGroup> groups = new HashMap<>();
    private final Map<String, PhysicalObject> environment = new HashMap<>();
    private final Map<String, Scenario> scenarios = new HashMap<>();
    private final Map<String, Story> subStories = new HashMap<>();

    private final Map<String, ?>[] storages = new Map[]{actors, groups, environment, scenarios, subStories};

    public Story(String storyName) {
        this.storyName = storyName;
    }

    public String getStoryName() {
        return storyName;
    }

    static void describeMany(StringBuilder sb, Map data) {
        for (var i : data.values())
            sb
                    .append(">")
                    .append(((Describable) i).describe())
                    .append("\n");
    }

    public final void addActor(String internalName, Human other) {
        actors.put(internalName, other);
    }

    public final void addGroup(String internalName, HumanGroup other) {
        groups.put(internalName, other);
    }

    public final void addEnvironment(String internalName, PhysicalObject other) {
        environment.put(internalName, other);
    }

    public final void addScenario(String internalName, Scenario other) {
        scenarios.put(internalName, other);
    }

    public final Human getActor(String name) {
        return actors.get(name);
    }

    public final HumanGroup getGroup(String name) {
        return groups.get(name);
    }

    public final PhysicalObject getEnvironment(String name) {
        return environment.get(name);
    }

    public final Scenario getScenario(String name) {
        return scenarios.get(name);
    }

    public final Story getSubStory(String name) {
        return subStories.get(name);
    }

    public Map<String, Human> getActors() {
        return actors;
    }

    public Map<String, HumanGroup> getGroups() {
        return groups;
    }

    public Map<String, PhysicalObject> getEnvironment() {
        return environment;
    }

    public Map<String, Scenario> getScenarios() {
        return scenarios;
    }

    public Map<String, Story> getSubStories() {
        return subStories;
    }

    // DI- and annotation- related:

    public CompiledStory compile() {
        StoryItemProviderProcessor.process(this, this);
        AbleProcessor.process(this);
        SetupMethodProcessor.process(this);
        CreateScenarioProcessor.process(this);

        for (var i : groups.values()) i.apply();

        return new CompiledStory(this);
    }

    public final void addSubStory(String internalName, Story story) {
        subStories.put(internalName, story);
        StoryItemProviderProcessor.process(this, story);
        StoryInjectProcessor.process(this, story);
        story.compile();
    }

    public final Object getProvidedObject(String identifier, Class targetClass) {
        for (Map<String, ?> storage : storages) {
            Object val = storage.get(identifier);
            if (val == null) continue;
            if (targetClass.isAssignableFrom(val.getClass()))
                return targetClass.cast(val);
        }
        return null;
    }

    @Override
    public String describe() {
        return compile().tell();
    }

    public static class CompiledStory {

        private final Story story;

        public CompiledStory(Story story) {
            this.story = story;
        }

        public String tell() {
            var sb = new StringBuilder("(STORY): %s: \n".formatted(story.getStoryName()));

            sb.append("\n(STORY): Окружение:\n");
            describeMany(sb, story.getEnvironment());

            sb.append("\n(STORY): Участники:\n");
            describeMany(sb, story.getActors());

            sb.append("\n(STORY): Группы:\n");
            describeMany(sb, story.getGroups());

            sb.append("\n(STORY): Сценарии:\n");
            describeMany(sb, story.getScenarios());

            return sb.toString();
        }
    }
}
