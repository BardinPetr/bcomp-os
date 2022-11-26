package ru.bardinpetr.itmo.lab_4.abilities;

import ru.bardinpetr.itmo.lab_4.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_4.properties.interfaces.Modifiable;
import ru.bardinpetr.itmo.lab_4.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.things.tool.Tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Ability implements Modifiable, Describable, Cloneable {
    private List<IModifier> modifiers = new ArrayList<>();

    private String abilityType = getClass().getName();
    private String abilityName = "";

    @Deprecated
    protected Ability(String abilityType, String abilityName) {
        this.abilityType = abilityType;
        this.abilityName = abilityName;
    }

    @Deprecated
    protected Ability(String abilityType) {
        this.abilityType = abilityType;
    }

    protected Ability() {}

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

    @Deprecated
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

    /**
     * Check if ability was not modified
     */
    public boolean isPure() {
        return getModifiers().size() == 0;
    }

    @Override
    public List<IModifier> getModifiers() {
        return modifiers;
    }

    @Deprecated
    public String getAbilityType() {
        return abilityType;
    }

    @Deprecated
    public String getAbilityName() {
        return abilityName;
    }

    @Deprecated
    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ability ability = (Ability) o;
        return modifiers.equals(ability.modifiers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modifiers);
    }

    @Override
    public Ability clone() {
        try {
            Ability clone = (Ability) super.clone();
            clone.modifiers = new ArrayList<>(modifiers);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
