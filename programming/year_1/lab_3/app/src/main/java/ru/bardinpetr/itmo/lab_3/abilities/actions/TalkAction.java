package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class TalkAction extends Ability {
    public static final String TYPE = "болтать";

    public TalkAction() {
        super(TYPE);
    }

    @Override
    public String getVerb() {
        return "болтает";
    }

    @Override
    protected String getObjectPreposition() {
        return "с";
    }
}
