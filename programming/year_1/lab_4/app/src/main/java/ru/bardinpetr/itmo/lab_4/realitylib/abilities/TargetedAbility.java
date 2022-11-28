package ru.bardinpetr.itmo.lab_4.realitylib.abilities;

import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

import java.util.Objects;

public abstract class TargetedAbility extends Ability {

    protected PhysicalObject targetObject;

    public PhysicalObject getTargetObject() {
        return targetObject;
    }

    public TargetedAbility setTargetObject(PhysicalObject targetObject) {
        this.targetObject = targetObject;
        return this;
    }

    @Override
    protected String getDescription() {
        return "%s %s".formatted(getObjectPreposition(), targetObject.getPhysicalObjectName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TargetedAbility that = (TargetedAbility) o;

        return Objects.equals(targetObject, that.targetObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetObject);
    }

    @Override
    public String toString() {
        return "TargetedAbility{" +
                "targetObject=" + targetObject +
                "} " + super.toString();
    }
}
