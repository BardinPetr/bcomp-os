package ru.bardinpetr.itmo.lab_4.properties.models;

import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;

public enum Brightness implements IModifier {
    DARK("темный"), BRIGHT("светлый");

    private final String text;

    Brightness(String text) {
        this.text = text;
    }

    public String getType() {
        return "яркость";
    }

    @Override
    public String getValue() {
        return text;
    }
}
