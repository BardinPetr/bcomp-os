package ru.bardinpetr.itmo.lab_3.properties.models;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public enum WaterType implements IModifier {
    STILL, SODA;

    @Override
    public String getPreposition() {
        return "";
    }

    @Override
    public String getType() {
        return "вид воды";
    }

    @Override
    public String getValue() {
        return name();
    }
}
