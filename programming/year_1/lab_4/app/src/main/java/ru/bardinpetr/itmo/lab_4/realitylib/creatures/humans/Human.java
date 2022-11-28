package ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans;


import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.errors.AbilityNotFoundException;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.Creature;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces.Friendable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces.ICommonHumanAbilities;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces.IPerforming;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.ScenarioActionFactory;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.TextualScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IAbilityConfigurationRunnable;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.Scriptable;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.Clothing;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.actions.LiveAction;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.actions.WearAction;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.BrotherModifier;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.FriendModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.modifiers.HasModifier;

import java.util.*;

public class Human extends Creature implements IPerforming, Scriptable, ICommonHumanAbilities, Friendable {
    private final Map<String, Ability> namedAbilities = new HashMap<>();
    private final Set<Class> pureAbilities = new HashSet<>();

    private final Scenario scenario = new TextualScenario();

    public Human(String name, String patronymic, String surname) {
        super(name, patronymic, surname);
        addAbility("wear", new WearAction());
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

        sb.append("Человек %s\n* имеет способности: \n".formatted(getFullName()));
        for (var i : getPureAbilities())
            sb.append("%s, ".formatted(i.getSimpleName()));

        sb.append("\n* обычно делает: \n");
        for (var i : getAbilities())
            sb.append("-* %s\n".formatted(i.describe()));

        if (getModifiers().size() > 0) {
            sb
                    .append("* обладает свойствами: \n")
                    .append(describeMods())
                    .append("\n");
        }

        return sb.toString();
    }

    public void live(Place place) {
        try {
            ((LiveAction) getAbilityByName("live")).setPlace(place);
        } catch (AbilityNotFoundException e) {
            addAbility("live", new LiveAction(place));
        }
    }

    public void wear(Clothing thing) {
        ((WearAction) getAbilityByName("wear")).putOn(thing);
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
        return ScenarioActionFactory.getInstance().newScenarioAction(
                this, baseAbility.clone(), conf
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Human human = (Human) o;

        if (!getAbilities().equals(human.getAbilities())) return false;
        return scenario != null ? scenario.equals(human.scenario) : human.scenario == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAbilities(), scenario);
    }

    @Override
    public String toString() {
        return "Human{abilities=%s, scenario=%s, %s}"
                .formatted(
                        getAbilities(),
                        scenario.print(),
                        super.toString()
                );
    }
}
