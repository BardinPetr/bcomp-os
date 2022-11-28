package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TargetedAbility;

public class LookLikeAction extends TargetedAbility {
    public LookLikeAction() {

    }

    @Override
    public String getVerb() {
        return "выглядит";
    }

    @Override
    protected String getObjectPreposition() {
        return "как";
    }

    @Override
    public String toString() {
        return "LookLikeAction{} %s".formatted(super.toString());
    }
}
