package ru.bardinpetr.itmo.lab_3.properties.models;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public enum Degree implements IModifier {
    VERY("очень");

    private final String text;

    Degree(String text) {
        this.text = text;
    }

    public String getType() {
        return "степень";
    }

    @Override
    public String getValue() {
        return text;
    }
}
