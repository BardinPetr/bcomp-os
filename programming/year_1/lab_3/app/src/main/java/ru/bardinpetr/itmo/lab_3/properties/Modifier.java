package ru.bardinpetr.itmo.lab_3.properties;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;

import static ru.bardinpetr.itmo.lab_3.tools.SpecialFormatter.joinNullableStrings;

abstract public class Modifier implements Describable {
    abstract public String getPreposition();

    abstract public String getType();

    abstract public String getValue();

    @Override
    public String describe() {
        return joinNullableStrings(" ", getPreposition(), getType(), getValue());
    }
}
