package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class HuntAction extends Ability {
    public static final String TYPE = "hunt";

    public HuntAction() {
        super(TYPE);
    }

    @Override
    public String getVerb() {
        return "охотиться";
    }

    @Override
    protected String getObjectPreposition() {
        return "на";
    }

    @Override
    public String toString() {
        return "HuntAction{} %s".formatted(super.toString());
    }
}
