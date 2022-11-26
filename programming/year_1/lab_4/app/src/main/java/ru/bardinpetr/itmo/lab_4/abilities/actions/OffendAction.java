package ru.bardinpetr.itmo.lab_4.abilities.actions;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;

public class OffendAction extends Ability {
    public static final String TYPE = "обижать";

    public OffendAction() {
        super(TYPE);
    }

    @Override
    public String getVerb() {
        return "обижать";
    }

    @Override
    public String toString() {
        return "OffendAction{} %s".formatted(super.toString());
    }
}
