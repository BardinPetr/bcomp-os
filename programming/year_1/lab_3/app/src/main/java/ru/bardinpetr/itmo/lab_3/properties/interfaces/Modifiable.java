package ru.bardinpetr.itmo.lab_3.properties.interfaces;

import ru.bardinpetr.itmo.lab_3.properties.Modifier;
import ru.bardinpetr.itmo.lab_3.tools.SpecialFormatter;

import java.util.List;

public interface Modifiable {
    void applyModifier(Modifier mod);

    List<Modifier> getModifiers();

    default String describeMods() {
        return SpecialFormatter.format(getModifiers());
    }
}
