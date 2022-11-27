package ru.bardinpetr.itmo.lab_4.realitylib.abilities;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.AlteringModifiable;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IAlteringModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.tool.Tool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Ability implements AlteringModifiable, Describable, Cloneable {
    private Map<Class, IAlteringModifier> modifiers = new HashMap<>();

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

    protected Ability() {
    }

    abstract protected String getVerb();

    protected String getDescription() {
        return "";
    }

    protected String getObjectPreposition() {
        return "";
    }

    public AbilityResult execute(Human self) {
        return new AbilityResult("done");
    }

    @Override
    public String describe() {
        return "%s %s".formatted(getVerb(), getDescription());
    }

    public String perform() {
        return getVerb();
    }

    @Deprecated(forRemoval = true)
    public String performWithOn(Tool tool, PhysicalObject object) {
//        StringBuilder sb = new StringBuilder();
//        if (tool != null) sb.append("при помощи %s ".formatted(tool.apply(object)));
//        sb.append("%s ".formatted(getVerb()));
//        if (getModifiers().size() > 0)
//            sb.append("(%s) ".formatted(describeMods()));
//        sb.append(getDescription());
//        if (object != null) sb.append("%s %s".formatted(getObjectPreposition(), object.getPhysicalObjectName()));
        return getVerb();
    }

    @Override
    public Map<Class, IAlteringModifier> getModifierMapping() {
        return modifiers;
    }

    @Override
    public Ability setModifier(Class modClass, Object modValue) {
        AlteringModifiable.super.setModifier(modClass, modValue);
        return this;
    }

    @Override
    public Ability applyModifier(IModifier mod) {
        AlteringModifiable.super.applyModifier(mod);
        return this;
    }

    /**
     * Check if ability was not modified
     */
    public boolean isPure() {
        return getModifiers().size() == 0;
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
            clone.modifiers = new HashMap<>(modifiers);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
