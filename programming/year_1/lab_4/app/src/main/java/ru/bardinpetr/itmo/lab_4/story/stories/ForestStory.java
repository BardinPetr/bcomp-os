package ru.bardinpetr.itmo.lab_4.story.stories;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TooledAbility;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.TextualScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.Able;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.ScenarioFor;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.StoryActor;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.StoryPlace;
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
import ru.bardinpetr.itmo.lab_4.story.things.places.House;

public class ForestStory extends Story {

    @StoryPlace
    private final Place home = new House("домик", new double[] {1.2, 1.5}, 100);

    @StoryPlace
    private final Place river = new Place("Река", new double[]{32.2, 234.2});

    @StoryPlace
    private final Place forest = new Place("Лес", new double[]{1.3, 2.2});

    @StoryActor
    @Able(CreateAction.class)
    @Able(CrossObstacleAction.class)
    @Able(FindFoodAction.class)
    @Able(WantAction.class)
    @Able(TooledAbility.class)
    @Able(GoAction.class)
    private final HumanGroup littleManGroup = new HumanGroup("коротышки");

    //    @Setup
    void environmentSetup() {
        forest.applyModifier(new PlaceModifier(PlaceModifier.PlaceRelation.IN_BEHIND, river));
    }

    @ScenarioFor("littleManGroup")
    Scenario[] scenarioFindFood() {
        // Boat
        Thing bark = new Thing("birch bark");
        Tool boat = new Tool("лодка") {
            @Override
            public String apply(PhysicalObject target) {
                return "двигаться на %s по %s".formatted(getPhysicalObjectName(), target.describe());
            }
        };
        boat.setModifier(MaterialModifier.class, bark);

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

//        scenario.addIf(new IScenarioAction() {
//            @Override
//            public String execute() {
//                return ;
//            }
//        });

        scenarioGoForest.addThen(littleManGroup.perform(
                CreateAction.class,
                (ability, ctx) -> ((CreateAction) ability).setTargetObject(river)
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
