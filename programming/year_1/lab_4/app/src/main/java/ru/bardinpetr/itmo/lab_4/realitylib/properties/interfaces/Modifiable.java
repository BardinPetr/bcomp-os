package ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces;

import java.util.List;

public interface Modifiable {
    Modifiable applyModifier(IModifier mod);

    List<IModifier> getModifiers();

    default String describeMods() {
        List<IModifier> mods = getModifiers();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mods.size(); i++) {
            sb.append(mods.get(i).describe());
            if (i != mods.size() - 1) sb.append("; ");
        }
        return sb.toString();
    }
}
