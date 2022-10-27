package ru.bardinpetr.itmo.lab_3.properties;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;

public class DescribingModifier extends Modifier {

    private final Describable activity;

    public DescribingModifier(Describable activity) {
        this.activity = activity;
    }

    @Override
    public String getPreposition() {
        return "";
    }

    @Override
    public String getType() {
        return "";
    }

    @Override
    public String getValue() {
        return this.activity.describe();
    }
}
