package ru.bardinpetr.itmo.lab_3.properties.interfaces;

import ru.bardinpetr.itmo.lab_3.properties.Modifier;
import ru.bardinpetr.itmo.lab_3.properties.ModifierType;
import ru.bardinpetr.itmo.lab_3.tools.SpecialFormatter;

import java.util.List;

public interface Modifiable {
    Modifiable applyModifier(Modifier mod);

    default Modifiable applyModifier(ModifierType modifierType, Object value) {
        return applyModifier(new Modifier(modifierType, value));
    }

    List<Modifier> getModifiers();

    default String describeMods() {
        return SpecialFormatter.format(getModifiers());
    }

    default Modifier getModifier(ModifierType modifierType) {
        for (Modifier modifier : getModifiers()) {
            if (modifier.getType() == modifierType)
                return modifier;
        }
        return null;
    }
}
