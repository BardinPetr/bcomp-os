package ru.bardinpetr.itmo.lab_3.creatures.humans;


import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.abilities.actions.LiveAction;
import ru.bardinpetr.itmo.lab_3.abilities.actions.WearAction;
import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.abilities.interfaces.IAble;
import ru.bardinpetr.itmo.lab_3.creatures.Creature;
import ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces.Friendable;
import ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces.ICommonHumanAbilities;
import ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces.IPerforming;
import ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces.IScriptable;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.BrotherModifier;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.FriendModifier;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.HasModifier;
import ru.bardinpetr.itmo.lab_3.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_3.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Tool;
import ru.bardinpetr.itmo.lab_3.things.place.Place;
import ru.bardinpetr.itmo.lab_3.things.wear.Clothing;

import java.util.ArrayList;
import java.util.List;

public class Human extends Creature implements IPerforming, IScriptable, ICommonHumanAbilities, Friendable {
    private final List<Ability> abilities = new ArrayList<>();

    private final WearAction wearAbility = new WearAction();

    private final Scenario scenario = new Scenario();

    public Human(String name, String patronymic, String surname) {
        super(name, patronymic, surname);
        addAbility(wearAbility);
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

        sb.append("%s умеет: \n".formatted(getFullName()));
        for (Describable i : getAbilities())
            sb.append("%s\n".formatted(i.describe()));

        sb.append("%s обладает свойствами: \n".formatted(getName()));
        sb.append(describeMods());
        sb.append("\n");

        sb.append("Имеет следующий сценарий: \n");
        sb.append(getScenario());
        sb.append("\n\n");

        return sb.toString();
    }

    public void live(Place place) {
        addAbility(new LiveAction(place));
    }

    public void wear(Clothing thing) {
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
}
