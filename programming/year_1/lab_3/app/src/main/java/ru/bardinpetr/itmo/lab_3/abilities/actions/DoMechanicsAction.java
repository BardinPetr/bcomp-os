package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

public class DoMechanicsAction extends Ability {
    public static final String TYPE = "работать механиком";

    public DoMechanicsAction() {
        super(TYPE);
    }

    @Override
    public String perform() {
        return "работать механиком";
    }
}
