package ru.bardinpetr.itmo.lab_4.story.modifiers.models;

import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;

public enum Count implements IModifier {
    MUCH("много");

    private final String text;

    Count(String text) {
        this.text = text;
    }

    public String getType() {
        return "количество";
    }

    @Override
    public String getValue() {
        return text;
    }
}
