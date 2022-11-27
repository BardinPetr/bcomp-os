package ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;

public interface IModifier extends Describable {
    String getType();

    Object getValue();

    @Override
    default String describe() {
        return getType() != null ? "%s: %s".formatted(getType(), getValue()) : "";
    }
}
