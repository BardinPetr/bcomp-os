package ru.bardinpetr.itmo.lab_4.story.creatures.animals;

import ru.bardinpetr.itmo.lab_4.realitylib.creatures.animals.Animal;

public class Dog extends Animal {
    public Dog(String name) {
        super("собака %s".formatted(name));
    }

    @Override
    public String toString() {
        return "Dog{%s}".formatted(super.toString());
    }
}
