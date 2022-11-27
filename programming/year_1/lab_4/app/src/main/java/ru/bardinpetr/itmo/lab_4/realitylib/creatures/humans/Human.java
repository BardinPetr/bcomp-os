package ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans;


import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.story.actions.LiveAction;
import ru.bardinpetr.itmo.lab_4.story.actions.WearAction;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.errors.AbilityNotFoundException;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.Creature;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces.Friendable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces.ICommonHumanAbilities;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces.IPerforming;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.BrotherModifier;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.FriendModifier;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.HasModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.StoryContext;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.TextualScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IAbilityConfigurationRunnable;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.Scriptable;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.Clothing;

import java.util.*;

public class Human extends Creature implements IPerforming, Scriptable, ICommonHumanAbilities, Friendable {
    private final Map<String, Ability> namedAbilities = new HashMap<>();
    private final Set<Class> pureAbilities = new HashSet<>();

    private final WearAction wearAbility = new WearAction();

    private final Scenario scenario = new TextualScenario();

    public Human(String name, String patronymic, String surname) {
        super(name, patronymic, surname);
    }

    public Human(String name) {
        this(name, "", "");
    }


    @Override
    public Map<String, Ability> getModifiedAbilities() {
        return namedAbilities;
    }

    @Override
    public Set<Class> getPureAbilities() {
        return pureAbilities;
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("%s\n* умеет: \n".formatted(getFullName()));
        var abilities = getAbilities();
        for (int i = 0; i < abilities.size(); i++)
            sb.append("%s\n".formatted(abilities.get(i).describe()));

        if (getModifiers().size() > 0) {
            sb.append("* обладает свойствами: \n");
            sb.append(describeMods());
            sb.append("\n");
        }

        String scenario = getScenario();
        if (!scenario.equals("")) {
            sb.append("* имеет следующий сценарий: \n");
            sb.append(getScenario());
        }

        sb.append("\n\n");
        return sb.toString();
    }

    public void live(Place place) {
        try {
            ((LiveAction) getAbility(LiveAction.class))
                    .setPlace(place);
        } catch (AbilityNotFoundException ex) {
            addAbility(new LiveAction(place));
        }
    }

    public void wear(Clothing thing) {
        if (wearAbility.getWearing().size() == 0)
            addAbility(wearAbility);
        wearAbility.putOn(thing);
    }

    public void have(PhysicalObject thing) {
        applyModifier(new HasModifier(thing));
    }

    protected void addBrotherReply(Human other) {
        applyModifier(new BrotherModifier(other));
    }

    public void addBrother(Human other) {
        applyModifier(new BrotherModifier(other));
        other.addBrotherReply(this);
    }

    public void addFriend(Human other) {
        applyModifier(new FriendModifier(other));
    }

    public String getScenario() {
        return scenario.print();
    }

    public void addScenario(Scenario newScenario) {
        scenario.append(newScenario);
    }

    @Override
    public IScenarioAction perform(Ability baseAbility, IAbilityConfigurationRunnable conf) {
        var ctx = new StoryContext();
        var ability = baseAbility.clone();

        return new IScenarioAction() {
            @Override
            public String execute() {
                Ability configuredAbility = conf.configure(ability, ctx);
                var result = configuredAbility.execute(Human.this);
                return "%s сделал %s".formatted(getName(), result);
            }

            @Override
            public String describe() {
                return conf.configure(ability, ctx).describe();
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Human human = (Human) o;

        if (!getAbilities().equals(human.getAbilities())) return false;
        if (wearAbility != null ? !wearAbility.equals(human.wearAbility) : human.wearAbility != null) return false;
        return scenario != null ? scenario.equals(human.scenario) : human.scenario == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAbilities(), wearAbility, scenario);
    }

    @Override
    public String toString() {
        return "Human{abilities=%s, wearAbility=%s, scenario=%s, %s}"
                .formatted(
                        getAbilities(),
                        wearAbility.toString(),
                        scenario.print(),
                        super.toString()
                );
    }
}
