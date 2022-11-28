package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TargetedAbility;

public class SeeAction extends TargetedAbility {
    public SeeAction() {
    }

    @Override
    public String getVerb() {
        return "видеть";
    }

    @Override
    public String toString() {
        return "SeeAction{} %s".formatted(super.toString());
    }
}
