package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TargetedAbility;

public class TalkAction extends TargetedAbility {
    public TalkAction() {
    }

    @Override
    public String getVerb() {
        return "болтает";
    }

    @Override
    protected String getObjectPreposition() {
        return "с";
    }

    @Override
    public String toString() {
        return "TalkAction{} %s".formatted(super.toString());
    }
}
