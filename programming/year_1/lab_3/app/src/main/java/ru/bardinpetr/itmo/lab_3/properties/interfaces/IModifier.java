package ru.bardinpetr.itmo.lab_3.properties.interfaces;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;

public interface IModifier extends Describable {
    String getType();

    String getValue();

    @Override
    default String describe() {
        return "%s: %s".formatted(getType(), getValue());
    }
}
