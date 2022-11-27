package ru.bardinpetr.itmo.lab_4.realitylib.creatures.animals;

import ru.bardinpetr.itmo.lab_4.realitylib.creatures.Creature;

public class Animal extends Creature {
    public Animal(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Animal{%s}".formatted(super.toString());
    }
}
