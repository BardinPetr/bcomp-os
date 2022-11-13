package ru.bardinpetr.itmo.lab_3.properties.models;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public enum Size implements IModifier {
    SMALL, NORMAL, LARGE;


    @Override
    public String getType() {
        return "размер";
    }

    @Override
    public String getValue() {
        return name();
    }
}
