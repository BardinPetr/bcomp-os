package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

import java.util.Objects;

public class BrotherModifier implements IModifier {
    private final Human other;

    public BrotherModifier(Human other) {
        this.other = other;
    }

    public String getType() {
        return "братья с";
    }

    @Override
    public String getValue() {
        return other.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrotherModifier that = (BrotherModifier) o;

        return Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {
        return other != null ? other.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BrotherModifier{other=%s}".formatted(other);
    }
}
