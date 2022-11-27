package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;

public class DrawAction extends Ability {
    public static final String TYPE = "рисовать";

    public DrawAction() {
        super(TYPE);
    }

    @Override
    public String getVerb() {
        return "рисовать";
    }

    @Override
    public String toString() {
        return "DrawAction{} %s".formatted(super.toString());
    }
}
