package ru.bardinpetr.itmo.lab_4.creatures.interfaces;

public interface Nameable {
    String getName();

    default String getFullName() {
        return getName();
    }
}
