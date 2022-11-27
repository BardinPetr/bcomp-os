package ru.bardinpetr.itmo.lab_4.story.modifiers.models;

import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;

public enum Difficulty implements IModifier {
    DIFFICULT("сложно"), EASY("легко");

    private final String text;

    Difficulty(String text) {
        this.text = text;
    }

    public String getType() {
        return "сложность";
    }

    @Override
    public String getValue() {
        return text;
    }
}
