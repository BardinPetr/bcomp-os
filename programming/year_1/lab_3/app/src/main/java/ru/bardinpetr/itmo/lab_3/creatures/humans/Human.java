package ru.bardinpetr.itmo.lab_3.creatures.humans;


import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.abilities.actions.LiveAction;
import ru.bardinpetr.itmo.lab_3.abilities.actions.WearAction;
import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.creatures.Creature;
import ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces.Friendable;
import ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces.ICommonHumanAbilities;
import ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces.IPerforming;
import ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces.Scriptable;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.BrotherModifier;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.FriendModifier;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.HasModifier;
import ru.bardinpetr.itmo.lab_3.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.place.Place;
import ru.bardinpetr.itmo.lab_3.things.wear.Clothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Human extends Creature implements IPerforming, Scriptable, ICommonHumanAbilities, Friendable {
    private final List<Ability> abilities = new ArrayList<>();

    private final WearAction wearAbility = new WearAction();

    private final Scenario scenario = new Scenario();

    public Human(String name, String patronymic, String surname) {
        super(name, patronymic, surname);
    }

    public Human(String name) {
        this(name, "", "");
    }

    @Override
    public void addAbility(Ability ability) {
        abilities.add(ability);
    }

    @Override
    public List<Ability> getAbilities() {
        return abilities;
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("%s\n* умеет: \n".formatted(getFullName()));
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
        addAbility(new LiveAction(place));
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

        if (abilities != null ? !abilities.equals(human.abilities) : human.abilities != null) return false;
        if (wearAbility != null ? !wearAbility.equals(human.wearAbility) : human.wearAbility != null) return false;
        return scenario != null ? scenario.equals(human.scenario) : human.scenario == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), abilities, wearAbility, scenario);
    }

    @Override
    public String toString() {
        return "Human{abilities=%s, wearAbility=%s, scenario=%s, %s}"
                .formatted(
                        abilities,
                        wearAbility.toString(),
                        scenario.print(),
                        super.toString()
                );
    }


}
