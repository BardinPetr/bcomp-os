package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class LookLikeAction extends Ability {
    public static final String TYPE = "выглядеть";

    public LookLikeAction() {
        super(TYPE);
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
