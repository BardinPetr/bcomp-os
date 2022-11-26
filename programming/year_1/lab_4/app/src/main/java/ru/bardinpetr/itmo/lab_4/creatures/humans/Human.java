package ru.bardinpetr.itmo.lab_4.creatures.humans;


import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.abilities.actions.LiveAction;
import ru.bardinpetr.itmo.lab_4.abilities.actions.WearAction;
import ru.bardinpetr.itmo.lab_4.abilities.errors.AbilityNotFoundException;
import ru.bardinpetr.itmo.lab_4.creatures.Creature;
import ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces.Friendable;
import ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces.ICommonHumanAbilities;
import ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces.IPerforming;
import ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces.Scriptable;
import ru.bardinpetr.itmo.lab_4.properties.modifiers.BrotherModifier;
import ru.bardinpetr.itmo.lab_4.properties.modifiers.FriendModifier;
import ru.bardinpetr.itmo.lab_4.properties.modifiers.HasModifier;
import ru.bardinpetr.itmo.lab_4.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.things.place.Place;
import ru.bardinpetr.itmo.lab_4.things.wear.Clothing;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Human extends Creature implements IPerforming, Scriptable, ICommonHumanAbilities, Friendable {
    private final Map<String, Ability> namedAbilities = new HashMap<>();
    private final Map<Class, Ability> typedAbilities = new HashMap<>();

    private final WearAction wearAbility = new WearAction();

    private final Scenario scenario = new Scenario();

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
    public Map<Class, Ability> getPureAbilities() {
        return typedAbilities;
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
