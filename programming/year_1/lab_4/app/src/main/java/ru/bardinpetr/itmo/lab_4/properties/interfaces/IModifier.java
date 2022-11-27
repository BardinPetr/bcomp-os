package ru.bardinpetr.itmo.lab_4.properties.interfaces;

import ru.bardinpetr.itmo.lab_4.abilities.interfaces.Describable;

public interface IModifier extends Describable {
    String getType();

    Object getValue();

    @Override
    default String describe() {
        return "%s: %s".formatted(getType(), getValue());
    }
}
