package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.AbilityResult;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TargetedAbility;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

public class CreateAction extends TargetedAbility {

    @Override
    protected String getVerb() {
        return "создавать";
    }

    @Override
    protected String getDescription() {
        return this.targetObject.describe();
    }

    @Override
    public AbilityResult execute(Human self) {
        return new AbilityResult("сделал %s".formatted(this.targetObject.getPhysicalObjectName()));
    }
}
