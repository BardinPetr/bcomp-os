package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IAlteringModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

import java.util.Objects;

public class WithModifier implements IAlteringModifier {
    private PhysicalObject other;

    public String getType() {
        return "с";
    }

    public WithModifier(PhysicalObject other) {
        this.other = other;
    }

    public WithModifier() {}

    @Override
    public String getValue() {
        return other.describe();
    }

    @Override
    public void setValue(Object value) {
        other = (PhysicalObject) value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WithModifier that = (WithModifier) o;

        return Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {
        return other != null ? other.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WithModifier{other=%s}".formatted(other);
    }

}
