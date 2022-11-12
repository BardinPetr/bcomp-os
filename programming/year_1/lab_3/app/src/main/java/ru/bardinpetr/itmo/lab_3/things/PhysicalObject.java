package ru.bardinpetr.itmo.lab_3.things;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.Modifier;
import ru.bardinpetr.itmo.lab_3.properties.ModifierType;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.Modifiable;

import java.util.ArrayList;
import java.util.List;

public abstract class PhysicalObject implements Modifiable, Describable {
    private final List<Modifier> modifiers = new ArrayList<>();

    @Override
    public Modifiable applyModifier(Modifier mod) {
        modifiers.add(mod);
        return this;
    }

    @Override
    public List<Modifier> getModifiers() {
        return modifiers;
    }
}
