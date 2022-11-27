package ru.bardinpetr.itmo.lab_4.story.stories;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TooledAbility;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.TextualScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.SubStory;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.Able;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.ScenarioFor;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryInject;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryProvide;
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

public class ForestStory extends SubStory {

    @StoryProvide
    private final Place river = new Place("Река", new double[]{32.2, 234.2});

    @StoryProvide
    private final Place forest = new Place("Лес", new double[]{1.3, 2.2});

    @StoryInject
    @Able(CreateAction.class)
    @Able(CrossObstacleAction.class)
    @Able(FindFoodAction.class)
    @Able(WantAction.class)
    @Able(TooledAbility.class)
    @Able(GoAction.class)
    public HumanGroup littleManGroup;

    @StoryInject
    private Place home;


    public ForestStory() {
        super("forest story");
        forest.applyModifier(new PlaceModifier(PlaceModifier.PlaceRelation.IN_BEHIND, river));
    }

    @ScenarioFor("littleManGroup")
    public Scenario[] scenarioFindFood() {
        // Boat
        Thing bark = new Thing("березовая кора");
        Tool boat = new Tool("лодка") {
            @Override
            public String apply(PhysicalObject target) {
                return "двигаться на %s по %s".formatted(getPhysicalObjectName(), target.describe());
            }
        };
        boat.setModifier(MaterialModifier.class, bark);

        Human h = new Human("a");

        // Food
        var berries = new Plant("ягоды");
        var mushrooms = new Plant("грибы");
        var nuts = new Plant("орехи");

        // Bush
        var bush = new Place("Куст", new double[]{5.3, 2.2});
        bush.applyModifier(Size.LARGE);

        var saw = new Tool("пила") {
            @Override
            public String apply(PhysicalObject target) {
                return "пилить %s".formatted(target.describe());
            }
        };

        // Scenario about forest
        var scenarioGoForest = new TextualScenario();

        scenarioGoForest.newSentence();
        scenarioGoForest.addIf(littleManGroup.perform(
                WantAction.class,
                (ability, ctx) -> ((WantAction) ability)
                        .setWantedAction((inside_ctx) -> "еда")
        ));

        scenarioGoForest.addThen(littleManGroup.perform(
                CreateAction.class,
                (ability, ctx) -> ((CreateAction) ability).setTargetObject(boat)
        ));

        scenarioGoForest.addThen(littleManGroup.perform(
                CrossObstacleAction.class,
                (ability, ctx) -> ((CrossObstacleAction) ability)
                        .setMoveType(CrossObstacleAction.CrossType.VIA_WATER)
                        .setTargetObject(river)
                        .setTool(boat)
        ));

        var findFoodScenarioAction = littleManGroup.perform(
                FindFoodAction.class,
                (ability, ctx) -> ((FindFoodAction) ability)
                        .setFoodTypes(new Eatable[]{berries, mushrooms, nuts})
                        .setModifier(PlaceModifier.class, forest)
                        .applyModifier(new ReasonModifier(
                                Difficulty.DIFFICULT,
                                Size.SMALL
                        ))
        );

        scenarioGoForest.addThen(findFoodScenarioAction);


        // Scenario of how it is hard
        var scenarioTakeFood = new TextualScenario();

        scenarioTakeFood.newSentence();
        scenarioTakeFood.addIf(littleManGroup.perform(
                WantAction.class,
                (ability, ctx) -> ((WantAction) ability)
                        .setWantedAction(findFoodScenarioAction)
        ));

        scenarioTakeFood.addThen(littleManGroup.perform(
                GoAction.class,
                (ability, ctx) -> ((GoAction) ability)
                        .setPlace(bush)
                        .setModifier(WithModifier.class, saw)
        ));

        scenarioTakeFood.addThen(littleManGroup.perform(
                TooledAbility.class,
                (ability, ctx) -> ((TooledAbility) ability)
                        .setTool(saw)
                        .setTargetObject(nuts)
                        .applyModifier(new ActivityReasonModifier(
                                Size.SMALL
                        ))
        ));

        scenarioTakeFood.addThen(littleManGroup.perform(
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

        scenarioTakeFood.addThen(littleManGroup.perform(
                TooledAbility.class,
                (ability, ctx) -> ((TooledAbility) ability)
                        .setTool(saw)
                        .setTargetObject(mushrooms)
                        .applyModifier(new GoalModifier("разрезать на части"))
        ));

        scenarioTakeFood.addThen(littleManGroup.perform(
                GoAction.class,
                (ability, ctx) -> ((GoAction) ability)
                        .setPlace(home)
                        .applyModifier(new WithModifier(mushrooms))
        ));


        return new Scenario[]{
                scenarioGoForest,
                scenarioTakeFood
        };
    }
}
