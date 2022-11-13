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
    private String abilityName;

    protected Ability(String abilityType, String abilityName) {
        this.abilityType = abilityType;
        this.abilityName = abilityName;
    }

    protected Ability(String abilityType) {
        this.abilityType = abilityType;
        this.abilityName = abilityType;
    }

    abstract public String perform();

    public String performWithOn(Tool tool, PhysicalObject object) {
        StringBuilder sb = new StringBuilder();
        if (tool != null)
            sb.append("при помощи %s".formatted(tool.describe()));
        else
            sb.append(perform());
        sb.append(" ");
        if (object != null)
            sb.append(object.getPhysicalObjectName());
        return sb.toString();
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

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }
}
