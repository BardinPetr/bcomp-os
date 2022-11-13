package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class SeeAction extends Ability {
    public static final String TYPE = "видеть";

    public SeeAction() {
        super(TYPE);
    }

    @Override
    public String getVerb() {
        return "видеть";
    }
}
