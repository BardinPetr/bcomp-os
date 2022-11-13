package ru.bardinpetr.itmo.lab_3.properties.interfaces;

import ru.bardinpetr.itmo.lab_3.tools.SpecialFormatter;

import java.util.List;

public interface Modifiable {
    Modifiable applyModifier(IModifier mod);

    List<IModifier> getModifiers();

    default String describeMods() {
        return SpecialFormatter.format(getModifiers());
    }

    default IModifier getModifier(String modifierType) {
        for (IModifier modifier : getModifiers()) {
            if (modifier.getType().equals(modifierType))
                return modifier;
        }
        return null;
    }
}
