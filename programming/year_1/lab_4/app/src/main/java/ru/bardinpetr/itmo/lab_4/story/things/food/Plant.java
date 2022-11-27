package ru.bardinpetr.itmo.lab_4.story.things.food;

import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;

public class Plant extends Thing implements Eatable {
    private final Thing root;

    public Plant(String name) {
        super(name);
        root = new Thing("корень");
    }

    public Thing getRoot() {
        return root;
    }

    @Override
    public String eat() {
        return "есть %s".formatted(getPhysicalObjectName());
    }
}
