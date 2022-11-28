package ru.bardinpetr.itmo.lab_4.story.stories;

import ru.bardinpetr.itmo.lab_4.realitylib.creatures.animals.Animal;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.TextualScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryInject;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryProvide;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.initialization.CreateScenario;
import ru.bardinpetr.itmo.lab_4.story.creatures.animals.Bug;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.KnownForModifier;

public class NeznaikaStory extends Story {

    @StoryInject
    private HumanGroup allLittleManGroup;

    @StoryInject
    private Human neznaika;

    // scenarios

    @StoryProvide
    private final Scenario neznaikaScenario = new TextualScenario("neznaika scenario");

    public NeznaikaStory() {
        super("znaika stories");
    }

    @CreateScenario
    private void neznaikaScenario(Scenario scenario) {
        Animal bug = new Bug("майский");

        scenario
                .newSentence()
                .addIf("незнайка забрел в поле")
                .addThen(bug.perform());

        neznaika.applyModifier(
                new KnownForModifier(neznaikaScenario)
        );
    }
}
