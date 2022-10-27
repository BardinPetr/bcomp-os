package ru.bardinpetr.itmo.lab_3.abilities;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.Modifier;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.Modifiable;

import java.util.ArrayList;
import java.util.List;

abstract public class Ability implements Describable, Modifiable {
    private final List<Modifier> modifiers = new ArrayList<>();

    abstract public String getExecutionString();

    @Override
    public void applyModifier(Modifier mod) {
        modifiers.add(mod);
    }

    @Override
    public List<Modifier> getModifiers() {
        return modifiers;
    }

    @Override
    public String describe() {
        return "%s %s".formatted(getExecutionString(), describeMods());
    }
}
