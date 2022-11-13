package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class NameAction extends Ability {
    public static final String TYPE = "называть";
    private final String text;

    public NameAction(String text) {
        super(TYPE);
        this.text = text;
    }

    @Override
    public String getVerb() {
        return "называть";
    }

    @Override
    protected String getDescription() {
        return text;
    }
}
