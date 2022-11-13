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
        for (Describable i : getAbilities())
            sb.append("%s\n".formatted(i.describe()));

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
}
