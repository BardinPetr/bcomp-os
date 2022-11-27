package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

import java.util.Objects;

public class HasModifier implements IModifier {
    private final PhysicalObject thing;

    public HasModifier(PhysicalObject thing) {
        this.thing = thing;
    }

    public String getType() {
        return "имеет";
    }

    @Override
    public String getValue() {
        return thing.describe();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HasModifier that = (HasModifier) o;

        return Objects.equals(thing, that.thing);
    }

    @Override
    public int hashCode() {
        return thing != null ? thing.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "HasModifier{thing=%s}".formatted(thing);
    }
}
