package ru.bardinpetr.itmo.lab_3.properties;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;


import static ru.bardinpetr.itmo.lab_3.tools.SpecialFormatter.joinNullableStrings;

public class Modifier implements Describable {

    private final ModifierType modifierType;
    private final Object value;

    public Modifier(ModifierType modifierType, Object value) {
        this.modifierType = modifierType;
        this.value = value;
    }

    public String getValue() {
        return value.toString();
    }

    @Override
    public String describe() {
        return joinNullableStrings(" ", modifierType.getPreposition(), modifierType.getType(), getValue());
    }

    public ModifierType getType() {
        return modifierType;
    }
}
