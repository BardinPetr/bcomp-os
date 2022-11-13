package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class TimeModifier implements IModifier {
    private final String time;

    public TimeModifier(String time) {
        this.time = time;
    }

    @Override
    public String getType() {
        return "время";
    }

    @Override
    public String getValue() {
        return time;
    }
}
