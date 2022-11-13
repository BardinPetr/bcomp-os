package ru.bardinpetr.itmo.lab_3.properties.models;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public enum Politeness implements IModifier {
    POLITE, IMPOLITE;

    @Override
    public String getType() {
        return "имеет вежливость";
    }

    @Override
    public String getValue() {
        return name();
    }
}
