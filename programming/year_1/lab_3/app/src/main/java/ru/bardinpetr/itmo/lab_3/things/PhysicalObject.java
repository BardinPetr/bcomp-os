package ru.bardinpetr.itmo.lab_3.things;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.Modifiable;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

import java.util.ArrayList;
import java.util.List;

public abstract class PhysicalObject implements Modifiable, Describable {
    private final List<IModifier> modifiers = new ArrayList<>();

    @Override
    public String describe() {
        return getPhysicalObjectName();
    }

    public abstract String getPhysicalObjectName();

    @Override
    public Modifiable applyModifier(IModifier mod) {
        modifiers.add(mod);
        return this;
    }

    @Override
    public List<IModifier> getModifiers() {
        return modifiers;
    }
}
