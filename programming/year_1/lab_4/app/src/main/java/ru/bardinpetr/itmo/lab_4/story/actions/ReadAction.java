package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.AbilityResult;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TargetedAbility;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

public class ReadAction extends TargetedAbility {

    @Override
    protected String getVerb() {
        return "читать";
    }

    @Override
    protected String getDescription() {
        return this.targetObject.describe();
    }

    @Override
    public AbilityResult execute(PhysicalObject self) {
        return new AbilityResult("читал %s".formatted(this.targetObject.getPhysicalObjectName()));
    }
}
