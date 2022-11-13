package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class TargetModifier implements IModifier {
    public static final String TYPE = "получатель";
    private final String other;

    public TargetModifier(String other) {
        this.other = other;
    }

    @Override
    public String getPreposition() {
        return "";
    }

    @Override
    public String getType() {
        return "кому";
    }

    @Override
    public String getValue() {
        return other;
    }
}
