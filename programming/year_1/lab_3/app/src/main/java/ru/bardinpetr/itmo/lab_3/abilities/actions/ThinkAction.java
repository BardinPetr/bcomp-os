package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class ThinkAction extends Ability {
    public static final String TYPE = "думать";

    private final String text;

    public ThinkAction(String text) {
        super(TYPE);
        this.text = text;
    }

    @Override
    public String getVerb() {
        return "думать";
    }

    @Override
    protected String getDescription() {
        return "о %s".formatted(text);
    }
}
