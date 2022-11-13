package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class BrotherModifier implements IModifier {
    private final Human other;

    public BrotherModifier(Human other) {
        this.other = other;
    }

    public String getType() {
        return "братья с";
    }

    @Override
    public String getValue() {
        return other.getName();
    }
}
