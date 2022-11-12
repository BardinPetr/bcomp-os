package ru.bardinpetr.itmo.lab_3.properties;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.ModifierDescribable;

public enum ModifierType implements ModifierDescribable {
    PLACE("place", "in"), TIME, HEIGHT, REASON, WHAT, FROM;

    private final String modType;
    private final String modPreposition;

    ModifierType(String name, String preposition) {
        this.modType = name;
        this.modPreposition = preposition;
    }

    ModifierType() {
        modType = name();
        modPreposition = "";
    }

    @Override
    public String getPreposition() {
        return modPreposition;
    }

    @Override
    public String getType() {
        return modType;
    }

    @Override
    public String convertValue(Object data) {
        if (data instanceof Describable)
            return ((Describable) data).describe();
        return String.valueOf(data);
    }
}
