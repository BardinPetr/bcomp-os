package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

public class DrawAction extends Ability {
    public static final String TYPE = "рисовать";

    public DrawAction() {
        super(TYPE);
    }

    @Override
    public String perform() {
        return "рисовать";
    }
}
