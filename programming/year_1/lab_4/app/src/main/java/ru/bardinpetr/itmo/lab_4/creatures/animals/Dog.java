package ru.bardinpetr.itmo.lab_4.creatures.animals;

public class Dog extends Animal {
    public Dog(String name) {
        super("собака %s".formatted(name));
    }

    @Override
    public String toString() {
        return "Dog{%s}".formatted(super.toString());
    }
}
