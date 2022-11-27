package ru.bardinpetr.itmo.lab_4.realitylib.story;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
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

    public SubStory(String storyName) {
        this.storyName = storyName;
    }

    public void compile() {
        StoryItemProviderProcessor.process(this, this);
//        AbleProcessor.process(story);
//        ScenarioForProcessor.process(story);
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

    public void addActor(String internalName, Human other) {
        actors.put(internalName, other);
    }

    public void addGroup(String internalName, HumanGroup other) {
        groups.put(internalName, other);
    }

    public void addEnvironment(String internalName, PhysicalObject other) {
        environment.put(internalName, other);
    }

    public void addScenario(String internalName, Scenario other) {
        scenarios.put(internalName, other);
    }

    public void addSubstory(SubStory story) {
        StoryItemProviderProcessor.process(this, story);
    }
}
