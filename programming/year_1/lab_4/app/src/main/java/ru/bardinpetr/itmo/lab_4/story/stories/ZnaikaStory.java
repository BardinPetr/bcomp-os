package ru.bardinpetr.itmo.lab_4.story.stories;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.actions.WearAction;
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
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.initialization.SetupMethod;
import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.Clothing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.WearType;
import ru.bardinpetr.itmo.lab_4.story.actions.*;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.Count;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.MostOfModifier;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.NameModifier;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.ReasonModifier;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.WithModifier;

import java.util.List;

public class ZnaikaStory extends Story {

    @StoryInject
    @Able(ThinkAction.class)
    @Able(LikeAction.class)
    private HumanGroup allLittleManGroup;

    @StoryInject
    @Able(SitAction.class)
    @Able(LookLikeAction.class)
    @Able(ReadAction.class)
    private Human znaika;

    // scenarios

    @StoryProvide
    private final Scenario znaikaScenario = new TextualScenario("znaika scenario");

    @StoryProvide
    private final Scenario groupScenario = new TextualScenario("group with znaika scenario");

    // local use
    private final Thing table = new Thing("стол");
    private final Thing bed = new Thing("кровать");
    private final Thing room = new Thing("комната Знайки");
    private final Thing books = new Thing("книги");


    public ZnaikaStory() {
        super("znaika stories");
    }

    @SetupMethod
    private void setupImportance() {
        enum Importance implements IModifier {
            IMPORTANT("главный"), NOTIMPORTANT("не главный");

            private final String text;

            Importance(String text) {
                this.text = text;
            }

            @Override
            public String getType() {
                return "важность";
            }

            @Override
            public Object getValue() {
                return text;
            }
        }

        znaika.applyModifier(new MostOfModifier(
                Importance.IMPORTANT,
                allLittleManGroup
        ));
    }

    @SetupMethod
    private void clothing() {
        znaika.wear(new Clothing(WearType.SUIT, 31));
    }

    @SetupMethod
    private void name() {
        Ability know = new ThinkAction("знать");
        know.applyModifier(Count.MUCH);

        Ability read = new ReadAction().setTargetObject(books);

        know.applyModifier(new ReasonModifier(read));

        znaika.applyModifier(
                new ReasonModifier(
                        new NameModifier("знайка"),
                        know
                )
        );

    }

    @CreateScenario
    private void znaikaScenario(Scenario scenario) {
        var glasses = new Clothing(WearType.GLASSES, 6);

        znaika.addAbility("wear_glasses",
                new WearAction(List.of(glasses)));

        scenario
                .newSentence()
                .addThen(znaika.perform(SitAction.class,
                        (ability, ctx) -> ((SitAction) ability)
                                .setPlace(table.asPlace()))
                )
                .addThen(znaika.perform("wear_glasses",
                        (ability, ctx) -> ability)
                )
                .addThen(znaika.perform(ReadAction.class,
                        (ability, ctx) -> ((ReadAction) ability)
                                .setTargetObject(books))
                )
                .addThen(znaika.perform(LookLikeAction.class,
                        (ability, ctx) -> ((LookLikeAction) ability)
                                .setTargetObject(new Human("профессор")))
                );
    }

    @SetupMethod
    private void places() {
        znaika.have(room);
        room.applyModifier(new WithModifier(books));
    }

    @CreateScenario
    private void groupScenario(Scenario scenario) {
        class ExistAction extends Ability {

            private final String text;

            public ExistAction(String text) {
                this.text = text;
            }

            @Override
            protected String getVerb() {
                return "является";
            }

            @Override
            protected String getDescription() {
                return text;
            }
        }

        znaika.addAbility("smart", new ExistAction("умный от чтения"));

        scenario
                .newSentence()
                .addIf(znaika.perform(
                        "smart",
                        (ability, ctx) -> ability)
                )
                .addThen(allLittleManGroup.perform(
                        ThinkAction.class,
                        (ability, ctx) -> ((ThinkAction) ability)
                                .setText("необходимость слушаться")
                                .setModifier(NameModifier.class, znaika.getName())
                ))
                .addThen(allLittleManGroup.perform(
                        LikeAction.class,
                        (ability, ctx) -> ((LikeAction) ability)
                                .setDescribable(() -> znaika.getName())
                ));
    }
}
