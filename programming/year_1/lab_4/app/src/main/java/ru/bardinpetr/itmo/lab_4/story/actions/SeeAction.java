package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;

public class SeeAction extends Ability {
    public static final String TYPE = "видеть";

    public SeeAction() {
        super(TYPE);
    }

    @Override
    public String getVerb() {
        return "видеть";
    }

    @Override
    public String toString() {
        return "SeeAction{} %s".formatted(super.toString());
    }
}
