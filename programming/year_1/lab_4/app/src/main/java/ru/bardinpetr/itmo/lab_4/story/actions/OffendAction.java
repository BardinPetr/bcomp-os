package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TargetedAbility;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

public class OffendAction extends TargetedAbility {
    public OffendAction() {
    }

    @Override
    public String getVerb() {
        return "обижать";
    }

    @Override
    public String toString() {
        return "OffendAction{} %s".formatted(super.toString());
    }

    @Override
    public OffendAction setTargetObject(PhysicalObject targetObject) {
        super.setTargetObject(targetObject);
        return this;
    }
}
