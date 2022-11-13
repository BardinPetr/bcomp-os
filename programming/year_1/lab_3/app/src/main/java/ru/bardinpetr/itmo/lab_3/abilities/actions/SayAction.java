package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class SayAction extends Ability {
    public static final String TYPE = "говорить";

    private final String text;

    public SayAction(String text) {
        super(TYPE);
        this.text = text;
    }

    @Override
    public String getVerb() {
        return "говорит";
    }

    @Override
    protected String getDescription() {
        return text;
    }
}
