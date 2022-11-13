package ru.bardinpetr.itmo.lab_3.abilities;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.Modifiable;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Tool;

import java.util.ArrayList;
import java.util.List;

public abstract class Ability implements Modifiable, Describable {
    private final List<IModifier> modifiers = new ArrayList<>();
    private final String abilityType;

    protected Ability(String abilityType) {
        this.abilityType = abilityType;
    }

    abstract public String perform();

    public String performWith(Tool tool) {
        return "при помощи %s %s".formatted(tool.getPhysicalObjectName(), perform());
    }

    public String performOn(PhysicalObject object) {
        return "%s %s".formatted(perform(), object.getPhysicalObjectName());
    }

    public String performOnWith(Tool tool, PhysicalObject object) {
        return "при помощи %s %s".formatted(tool.describe(), tool.apply(object));
    }

    @Override
    public String describe() {
        return "%s %s".formatted(getAbilityType(), describeMods());
    }

    @Override
    public Modifiable applyModifier(IModifier mod) {
        modifiers.add(mod);
        return this;
    }

    @Override
    public List<IModifier> getModifiers() {
        return modifiers;
    }

    public String getAbilityType() {
        return abilityType;
    }
}
