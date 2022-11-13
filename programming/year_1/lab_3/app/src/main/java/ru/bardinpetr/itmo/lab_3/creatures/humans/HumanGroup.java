package ru.bardinpetr.itmo.lab_3.creatures.humans;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.place.Place;
import ru.bardinpetr.itmo.lab_3.tools.SpecialFormatter;

import java.util.ArrayList;
import java.util.List;

public class HumanGroup extends PhysicalObject implements Describable {
    private final List<Human> group = new ArrayList<>();

    private final String name;

    public HumanGroup(String name) {
        this.name = name;
    }

    public List<Human> getGroup() {
        return group;
    }

    public void add(Human human) {
        group.add(human);
    }

    @Override
    public String describe() {
        return SpecialFormatter.format(group);
    }

    @Override
    public String getPhysicalObjectName() {
        return name;
    }

    public void live(Place place) {
        for (int i = 0; i < group.size(); i++) {
            group.get(i).live(place);
        }
    }

    public Human getByName(String name) {
        for (int i = 0; i < group.size(); i++)
            if (group.get(i).getName().equals(name)) return group.get(i);
        return null;
    }

    public String getName() {
        return name;
    }
}
