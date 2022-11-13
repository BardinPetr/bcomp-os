package ru.bardinpetr.itmo.lab_3.abilities;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.Modifiable;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.tool.Tool;

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

    abstract protected String getVerb();

    protected String getDescription() {
        return "";
    }

    protected String getObjectPreposition() {
        return "";
    }

    public String perform() {
        return performWithOn(null, null);
    }

    public String performWithOn(Tool tool, PhysicalObject object) {
        StringBuilder sb = new StringBuilder();

        if (tool != null) sb.append("при помощи %s ".formatted(tool.apply(object)));

        sb.append("%s ".formatted(getVerb()));

        if (getModifiers().size() > 0)
            sb.append("(%s) ".formatted(describeMods()));

        sb.append(getDescription());

        if (object != null) sb.append("%s %s".formatted(getObjectPreposition(), object.getPhysicalObjectName()));


        return sb.toString();
    }

    @Override
    public String describe() {
        return performWithOn(null, null);
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
