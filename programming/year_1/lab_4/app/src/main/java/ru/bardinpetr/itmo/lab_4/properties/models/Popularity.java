package ru.bardinpetr.itmo.lab_4.properties.models;

import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;

public enum Popularity implements IModifier {
    POPULAR("известный"), UNPOPULAR("неизвестный");

    private final String text;

    Popularity(String text) {
        this.text = text;
    }

    public String getType() {
        return "популярность";
    }

    @Override
    public String getValue() {
        return text;
    }
}
