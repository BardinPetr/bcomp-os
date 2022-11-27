package ru.bardinpetr.itmo.lab_4.realitylib.story;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors.StoryInjectProcessor;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.processors.StoryItemProviderProcessor;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

import java.util.HashMap;
import java.util.Map;

public abstract class SubStory {
    private final String storyName;
    private final Map<String, Human> actors = new HashMap<>();
    private final Map<String, HumanGroup> groups = new HashMap<>();
    private final Map<String, PhysicalObject> environment = new HashMap<>();
    private final Map<String, Scenario> scenarios = new HashMap<>();
    private final Map<String, SubStory> subStories = new HashMap<>();

    private final Map<String, ?>[] storages = new Map[]{actors, groups, environment, scenarios, subStories};

    public SubStory(String storyName) {
        this.storyName = storyName;
    }

    public void compile() {
        StoryItemProviderProcessor.process(this, this);
    }

    public String getStoryName() {
        return storyName;
    }

    public String tell() {
        var sb = new StringBuilder("(STORY): %s: \n".formatted(getStoryName()));

        sb.append("\n(STORY): Окружение:\n");
        describeMany(sb, environment);

        sb.append("\n(STORY): Участники:\n");
        describeMany(sb, actors);

        sb.append("\n(STORY): Группы:\n");
        describeMany(sb, groups);

        sb.append("\n(STORY): Сценарии:\n");
        describeMany(sb, scenarios);

        return sb.toString();
    }

    private void describeMany(StringBuilder sb, Map data) {
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

    public final void addSubStory(String internalName, SubStory story) {
        subStories.put(internalName, story);
        StoryItemProviderProcessor.process(this, story);
        StoryInjectProcessor.process(this, story);
        story.compile();
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

    public final SubStory getSubStory(String name) {
        return subStories.get(name);
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
}
