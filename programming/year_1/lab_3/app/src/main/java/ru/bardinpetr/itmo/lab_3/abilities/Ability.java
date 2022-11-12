package ru.bardinpetr.itmo.lab_3.abilities;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Performable;
import ru.bardinpetr.itmo.lab_3.properties.Modifier;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.Modifiable;

import java.util.ArrayList;
import java.util.List;

public class Ability implements Modifiable, Describable, Performable {
    private final List<Modifier> modifiers = new ArrayList<>();
    private final AbilityType type;

    public Ability(AbilityType type) {
        this.type = type;
    }

    @Override
    public String describe() {
        return "%s %s".formatted(getType().perform(), describeMods());
    }

    @Override
    public String perform() {
        return "%s %s".formatted(getType().perform(), describeMods());
    }

    @Override
    public Modifiable applyModifier(Modifier mod) {
        modifiers.add(mod);
        return this;
    }

    @Override
    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public AbilityType getType() {
        return type;
    }
}
