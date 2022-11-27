package ru.bardinpetr.itmo.lab_4.story.stories;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TooledAbility;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.TextualScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.abilities.Able;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryInject;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryProvide;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.initialization.CreateScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.initialization.SetupMethod;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;
import ru.bardinpetr.itmo.lab_4.realitylib.things.tool.Tool;
import ru.bardinpetr.itmo.lab_4.story.actions.*;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.Difficulty;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.Size;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.*;
import ru.bardinpetr.itmo.lab_4.story.things.food.Eatable;
import ru.bardinpetr.itmo.lab_4.story.things.food.Plant;

public class ForestStory extends Story {

    // Places
    @StoryProvide
    private final Place river = new Place("Река", new double[]{32.2, 234.2});

    @StoryProvide
    private final Place forest = new Place("Лес", new double[]{1.3, 2.2});

    // Things
    @StoryProvide
    private final Thing bark = new Thing("березовая кора");

    @StoryProvide
    private final Tool boat = new Tool("лодка") {
        @Override
        public String apply(PhysicalObject target) {
            return "двигаться на %s по %s".formatted(getPhysicalObjectName(), target.describe());
        }
    };

    @StoryProvide
    Tool saw = new Tool("пила") {
        @Override
        public String apply(PhysicalObject target) {
            return "пилить %s".formatted(target.describe());
        }
    };

    @StoryProvide
    private final Place bush = new Place("Куст", new double[]{5.3, 2.2});
    @StoryProvide
    private final Plant berries = new Plant("Ягоды");
    @StoryProvide
    private final Plant mushrooms = new Plant("Грибы");
    @StoryProvide
    private final Plant nuts = new Plant("Орехи");


    // Scenarios
    @StoryProvide
    private final Scenario scenarioFindFood = new TextualScenario("find food scenario");

    @StoryProvide
    private final Scenario scenarioGetFood = new TextualScenario("get and bring food scenario");


    // Injected
    @StoryInject
    @Able(CreateAction.class)
    @Able(CrossObstacleAction.class)
    @Able(FindFoodAction.class)
    @Able(WantAction.class)
    @Able(TooledAbility.class)
    @Able(GoAction.class)
    private HumanGroup littleManGroup;

    @StoryInject
    private Place home;


    // Classic
    IScenarioAction findFoodScenarioAction = null;

    public ForestStory() {
        super("forest story");
    }

    @SetupMethod
    private void setupRiverRelated() {
        forest.applyModifier(new PlaceModifier(PlaceModifier.PlaceRelation.IN_BEHIND, river));
    }

    @SetupMethod
    private void setupBoat() {
        boat.setModifier(MaterialModifier.class, bark);
    }

    @SetupMethod
    private void setupEnvironment() {
        bush.applyModifier(Size.LARGE);
    }


    @CreateScenario
    private void scenarioFindFood(Scenario scenario) {
        // Bush
        scenario.newSentence();
        scenario.addIf(littleManGroup.perform(
                WantAction.class,
                (ability, ctx) -> ((WantAction) ability)
                        .setWantedAction((inside_ctx) -> "еда")
        ));

        scenario.addThen(littleManGroup.perform(
                CreateAction.class,
                (ability, ctx) -> ((CreateAction) ability).setTargetObject(boat)
        ));

        scenario.addThen(littleManGroup.perform(
                CrossObstacleAction.class,
                (ability, ctx) -> ((CrossObstacleAction) ability)
                        .setMoveType(CrossObstacleAction.CrossType.VIA_WATER)
                        .setTargetObject(river)
                        .setTool(boat)
        ));

        findFoodScenarioAction = littleManGroup.perform(
                FindFoodAction.class,
                (ability, ctx) -> ((FindFoodAction) ability)
                        .setFoodTypes(new Eatable[]{berries, mushrooms, nuts})
                        .setModifier(PlaceModifier.class, forest)
                        .applyModifier(new ReasonModifier(
                                Difficulty.DIFFICULT,
                                Size.SMALL
                        ))
        );

        scenario.addThen(findFoodScenarioAction);
    }

    @CreateScenario
    private void scenarioGetFood(Scenario scenario) {
        scenario.newSentence();
        scenario.addIf(littleManGroup.perform(
                WantAction.class,
                (ability, ctx) -> ((WantAction) ability)
                        .setWantedAction(findFoodScenarioAction)
        ));

        scenario.addThen(littleManGroup.perform(
                GoAction.class,
                (ability, ctx) -> ((GoAction) ability)
                        .setPlace(bush)
                        .setModifier(WithModifier.class, saw)
        ));

        scenario.addThen(littleManGroup.perform(
                TooledAbility.class,
                (ability, ctx) -> ((TooledAbility) ability)
                        .setTool(saw)
                        .setTargetObject(nuts)
                        .applyModifier(new ActivityReasonModifier(
                                Size.SMALL
                        ))
        ));

        scenario.addThen(littleManGroup.perform(
                TooledAbility.class,
                (ability, ctx) -> ((TooledAbility) ability)
                        .setTool(saw)
                        .setTargetObject(mushrooms)
                        .applyModifier(
                                new PlaceModifier(
                                        PlaceModifier.PlaceRelation.OVER,
                                        mushrooms.getRoot().asPlace()
                                )
                        )
        ));

        scenario.addThen(littleManGroup.perform(
                TooledAbility.class,
                (ability, ctx) -> ((TooledAbility) ability)
                        .setTool(saw)
                        .setTargetObject(mushrooms)
                        .applyModifier(new GoalModifier("разрезать на части"))
        ));

        scenario.addThen(littleManGroup.perform(
                GoAction.class,
                (ability, ctx) -> ((GoAction) ability)
                        .setPlace(home)
                        .applyModifier(new WithModifier(mushrooms))
        ));
    }
}
