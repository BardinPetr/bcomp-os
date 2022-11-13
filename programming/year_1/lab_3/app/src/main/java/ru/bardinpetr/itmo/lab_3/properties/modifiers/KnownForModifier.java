package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class KnownForModifier implements IModifier {
    public static final String TYPE = "прославлялся";
    private Describable describable;

    public KnownForModifier(Describable describable) {
        this.describable = describable;
    }

    @Override
    public String getPreposition() {
        return "тем что";
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getValue() {
        return describable.describe();
    }

    public void setDescribable(Describable describable) {
        this.describable = describable;
    }
}
