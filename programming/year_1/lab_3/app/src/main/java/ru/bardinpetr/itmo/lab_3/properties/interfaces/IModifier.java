package ru.bardinpetr.itmo.lab_3.properties.interfaces;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.tools.SpecialFormatter;

public interface IModifier extends Describable {
    default String getPreposition() {
        return "";
    }

    String getType();

    String getValue();

    @Override
    default String describe() {
        return SpecialFormatter.joinNullableStrings(" ", getPreposition(), getType(), getValue());
    }
}
