package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;

public class DoMechanicsAction extends Ability {
    public static final String TYPE = "mechanics";

    public DoMechanicsAction() {
        super(TYPE);
    }

    @Override
    public String getVerb() {
        return "работать механиком";
    }

    @Override
    public String toString() {
        return "DoMechanicsAction{} %s".formatted(super.toString());
    }
}
