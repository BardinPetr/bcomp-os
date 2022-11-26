package ru.bardinpetr.itmo.lab_4.properties.modifiers;

import ru.bardinpetr.itmo.lab_4.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;

import java.util.Objects;

public class KnownForModifier implements IModifier {
    private Describable describable;

    public KnownForModifier(Describable describable) {
        this.describable = describable;
    }

    public String getType() {
        return "прославлялся";
    }

    @Override
    public String getValue() {
        return "тем что %s".formatted(describable.describe());
    }

    public void setDescribable(Describable describable) {
        this.describable = describable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnownForModifier that = (KnownForModifier) o;

        return Objects.equals(describable, that.describable);
    }

    @Override
    public int hashCode() {
        return describable != null ? describable.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "KnownForModifier{describable=%s}".formatted(describable);
    }
}
