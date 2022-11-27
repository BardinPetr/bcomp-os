package ru.bardinpetr.itmo.lab_4.things;

import ru.bardinpetr.itmo.lab_4.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.properties.interfaces.AlteringModifiable;
import ru.bardinpetr.itmo.lab_4.properties.interfaces.IAlteringModifier;

import java.util.HashMap;
import java.util.Map;

public abstract class PhysicalObject implements AlteringModifiable, Describable {
    private final Map<Class, IAlteringModifier> modifiers = new HashMap<>();

    private double[] position = new double[]{0, 0};

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder(getPhysicalObjectName());
        if (getModifiers().size() > 0)
            sb.append(" (со свойствами: %s)".formatted(describeMods()));
        return sb.toString();
    }

    public abstract String getPhysicalObjectName();

    @Override
    public Map<Class, IAlteringModifier> getModifierMapping() {
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

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
}
