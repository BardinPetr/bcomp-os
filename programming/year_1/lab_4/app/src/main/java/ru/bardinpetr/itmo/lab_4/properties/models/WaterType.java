package ru.bardinpetr.itmo.lab_4.properties.models;

import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;

public enum WaterType implements IModifier {
    STILL("обычная"), SODA("газированная");
    private final String text;

    WaterType(String text) {
        this.text = text;
    }

    public String getType() {
        return "вид воды";
    }

    @Override
    public String getValue() {
        return text;
    }
}
