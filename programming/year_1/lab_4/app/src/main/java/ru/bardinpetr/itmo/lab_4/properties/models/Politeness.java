package ru.bardinpetr.itmo.lab_4.properties.models;

import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;

public enum Politeness implements IModifier {
    POLITE("вежливый"), IMPOLITE("не вежливый");
    private final String text;

    Politeness(String text) {
        this.text = text;
    }

    public String getType() {
        return "вежливость";
    }

    @Override
    public String getValue() {
        return text;
    }
}
