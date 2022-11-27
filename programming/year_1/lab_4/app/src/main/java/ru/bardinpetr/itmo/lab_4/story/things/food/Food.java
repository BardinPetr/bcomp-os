package ru.bardinpetr.itmo.lab_4.story.things.food;

import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;

public class Food extends Thing implements Eatable {
    public Food(String name) {
        super(name);
    }

    @Override
    public String eat() {
        return getPhysicalObjectName();
    }
}
