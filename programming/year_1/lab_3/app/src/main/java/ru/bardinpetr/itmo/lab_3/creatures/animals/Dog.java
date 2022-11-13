package ru.bardinpetr.itmo.lab_3.creatures.animals;

public class Dog extends Animal {
    public Dog(String name) {
        super("собака %s".formatted(name));
    }
}
