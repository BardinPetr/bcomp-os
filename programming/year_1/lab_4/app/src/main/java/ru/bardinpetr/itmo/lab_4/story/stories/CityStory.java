package ru.bardinpetr.itmo.lab_4.story.stories;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.actions.WearAction;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.animals.Animal;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.TextualScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.abilities.Able;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryInject;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryProvide;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.initialization.CreateScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;
import ru.bardinpetr.itmo.lab_4.story.actions.*;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.QuarrelState;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.CountModifier;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.TargetModifier;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.TimeModifier;

public class CityStory extends Story {

    @StoryInject
    private Place city;

    @StoryInject
    private Place house;

    @StoryInject
    @Able(SeeAction.class)
    @Able(TurnAction.class)
    @Able(HideAction.class)
    private HumanGroup littleGirlsGroup;

    @StoryInject
    @Able(LikeAction.class)
    private Human siropchik;

    @StoryInject
    @Able(LookLikeAction.class)
    @Able(GoAction.class)
    @Able(ThinkAction.class)
    @Able(SayAction.class)
    @Able(OffendAction.class)
    @Able(TalkAction.class)
    @Able(QuarrelAction.class)
    private Human neznaika;

    @StoryInject
    @Able(TalkAction.class)
    private Human gunka;

    // scenarios

    @StoryProvide
    private final Scenario siropchikScenario = new TextualScenario("Syropchik main scenario");
    @StoryProvide
    private final Scenario neznaikaAndGunkaScenario = new TextualScenario("Neznaika And Gunka talk");
    @StoryProvide
    private final Scenario neznaikaInCityScenario = new TextualScenario("Neznaika in the city");
    @StoryProvide
    private final Scenario littleGirlsGroupScenario = new TextualScenario("little Girls hide from neznaika");


    public CityStory() {
        super("in city stories");
    }

    @CreateScenario
    private void siropchikScenario(Scenario scenario) {
        scenario
                .newSentence()
                .addIf("сиропчика называть его по имени и отчеству")
                .addThen(siropchik.perform(LikeAction.class,
                        (ability, ctx) -> ((LikeAction) ability)
                                .setLike(true)
                                .setDescribable(() -> "называние")))
                .addElse()
                .addThen(siropchik.perform(LikeAction.class,
                        (ability, ctx) -> ((LikeAction) ability)
                                .setLike(false)
                                .setDescribable(() -> "называние")));
    }

    @CreateScenario
    private void neznaikaInCityScenario(Scenario scenario) {
        PhysicalObject parrot = new Animal("попугай");

        scenario
                .newSentence()
                .addIf(neznaika.perform(WearAction.class, (ability, ctx) -> ability))
                .addThen(neznaika.perform(LookLikeAction.class,
                        (ability, ctx) -> ((LookLikeAction) ability)
                                .setTargetObject(parrot))
                )
                .addThen(neznaika.perform(GoAction.class,
                        ((ability, ctx) -> ((GoAction) ability)
                                .setPlace(city)
                                .applyModifier(new TimeModifier("целыми днями"))))
                )
                .addThen(neznaika.perform(ThinkAction.class,
                        (ability, ctx) -> ((ThinkAction) ability)
                                .setText("сочинение небылиц"))
                )
                .addThen(neznaika.perform(SayAction.class,
                        (ability, ctx) -> ((SayAction) ability)
                                .setText("небылицы")
                                .applyModifier(new TargetModifier("всем")))
                )
                .newSentence()
                .addThen(neznaika.perform(OffendAction.class,
                        (ability, ctx) -> ((OffendAction) ability)
                                .setTargetObject(littleGirlsGroup)
                                .applyModifier(new TimeModifier("постоянно")))
                );
    }

    @CreateScenario
    private void neznaikaAndGunkaScenario(Scenario scenario) {
        IModifier countPerDay = new CountModifier(20);

        scenario
                .newSentence()
                .addThen(neznaika.perform(TalkAction.class,
                        (ability, ctx) -> ((TalkAction) ability)
                                .setTargetObject(gunka)
                                .applyModifier(new TimeModifier("часами")))
                )
                .newSentence()
                .addThen(neznaika.perform(QuarrelAction.class,
                        (ability, ctx) -> ((QuarrelAction) ability)
                                .setState(QuarrelState.IN_QUARREL)
                                .setTargetObject(gunka)
                                .applyModifier(countPerDay))
                )
                .addThen(neznaika.perform(QuarrelAction.class,
                        (ability, ctx) -> ((QuarrelAction) ability)
                                .setState(QuarrelState.NORMAL)
                                .setTargetObject(gunka)
                                .applyModifier(countPerDay))
                );
    }

    @CreateScenario
    private void littleGirlsGroupScenario(Scenario scenario) {
        scenario
                .newSentence()
                .addIf(littleGirlsGroup.perform(SeeAction.class,
                        (ability, ctx) -> ((SeeAction) ability)
                                .setTargetObject(neznaika))
                )
                .addThen(littleGirlsGroup.perform(TurnAction.class,
                        (ability, ctx) -> ((TurnAction) ability)
                                .setDir("обратно")
                                .applyModifier(new TimeModifier("сейчас же")))
                )
                .addThen(littleGirlsGroup.perform(HideAction.class,
                        (ability, ctx) -> ((HideAction) ability)
                                .setPlace(house))
                );
    }
}
