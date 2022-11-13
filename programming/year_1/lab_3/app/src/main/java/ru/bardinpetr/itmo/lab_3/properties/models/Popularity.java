package ru.bardinpetr.itmo.lab_3.properties.models;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public enum Popularity implements IModifier {
    POPULAR, UNPOPULAR;

    @Override
    public String getType() {
        return "имеет популярность";
    }

    @Override
    public String getValue() {
        return name();
    }
}
