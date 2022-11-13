package ru.bardinpetr.itmo.lab_3.things;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.Modifiable;

import java.util.ArrayList;
import java.util.List;

public abstract class PhysicalObject implements Modifiable, Describable {
    private final List<IModifier> modifiers = new ArrayList<>();

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder(getPhysicalObjectName());
        if (getModifiers().size() > 0)
            sb.append(" (со свойствами: %s)".formatted(describeMods()));
        return sb.toString();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhysicalObject that = (PhysicalObject) o;

        return modifiers.equals(that.modifiers);
    }

    @Override
    public int hashCode() {
        return modifiers.hashCode();
    }

    @Override
    public String toString() {
        return "PhysicalObject{modifiers=%s}".formatted(modifiers);
    }
}
