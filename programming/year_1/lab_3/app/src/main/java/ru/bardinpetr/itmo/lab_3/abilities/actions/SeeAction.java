package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

public class SeeAction extends Ability {
    public static final String TYPE = "видеть";

    public SeeAction() {
        super(TYPE);
    }

    @Override
    public String perform() {
        return "видеть";
    }
}
