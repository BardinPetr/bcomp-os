package ru.bardinpetr.itmo.lab_3.properties.models;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public enum Color implements IModifier {
    WHITE, CYAN, YELLOW_CANARY, ORANGE, GREEN;

    @Override
    public String getType() {
        return "цвет";
    }

    @Override
    public String getValue() {
        return name();
    }
}
