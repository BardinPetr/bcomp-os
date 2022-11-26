package ru.bardinpetr.itmo.lab_4.properties.modifiers;

import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;

import java.util.Objects;

public class TargetModifier implements IModifier {
    private final String other;

    public TargetModifier(String other) {
        this.other = other;
    }

    @Override
    public String getType() {
        return "получатель";
    }

    @Override
    public String getValue() {
        return other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TargetModifier that = (TargetModifier) o;

        return Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {
        return other != null ? other.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TargetModifier{other='%s'}".formatted(other);
    }
}
