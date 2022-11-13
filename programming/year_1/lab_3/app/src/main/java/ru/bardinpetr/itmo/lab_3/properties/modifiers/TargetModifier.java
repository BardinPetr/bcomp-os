package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class TargetModifier implements IModifier {
    private final String other;

    public TargetModifier(String other) {
        this.other = other;
    }

    @Override
    public String getType() {
        return "получатель";
    }

    @Override
    public String getValue() {
        return other;
    }
}
