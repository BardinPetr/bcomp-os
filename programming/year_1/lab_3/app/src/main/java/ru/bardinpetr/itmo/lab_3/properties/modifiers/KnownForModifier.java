package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

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
}
