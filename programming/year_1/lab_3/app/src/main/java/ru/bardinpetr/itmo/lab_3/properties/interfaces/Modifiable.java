package ru.bardinpetr.itmo.lab_3.properties.interfaces;

import java.util.List;

public interface Modifiable {
    Modifiable applyModifier(IModifier mod);

    List<IModifier> getModifiers();

    default String describeMods() {
        StringBuilder sb = new StringBuilder();
        List<IModifier> mods = getModifiers();
        for (int i = 0; i < mods.size(); i++) {
            sb.append(mods.get(i).describe());
            if (i != mods.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    default IModifier getModifier(String modifierType) {
        for (IModifier modifier : getModifiers()) {
            if (modifier.getType().equals(modifierType))
                return modifier;
        }
        return null;
    }
}
