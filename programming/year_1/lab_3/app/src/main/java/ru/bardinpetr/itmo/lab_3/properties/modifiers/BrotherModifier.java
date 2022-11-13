package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class BrotherModifier implements IModifier {
    public static final String TYPE = "брат";
    private final Human other;

    public BrotherModifier(Human other) {
        this.other = other;
    }

    @Override
    public String getPreposition() {
        return "с";
    }

    @Override
    public String getType() {
        return "братья";
    }

    @Override
    public String getValue() {
        return other.getName();
    }
}
