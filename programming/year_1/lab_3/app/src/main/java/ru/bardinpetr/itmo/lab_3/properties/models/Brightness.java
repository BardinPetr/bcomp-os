package ru.bardinpetr.itmo.lab_3.properties.models;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public enum Brightness implements IModifier {
    DARK, BRIGHT;

    @Override
    public String getType() {
        return "яркость";
    }

    @Override
    public String getValue() {
        return name();
    }
}
