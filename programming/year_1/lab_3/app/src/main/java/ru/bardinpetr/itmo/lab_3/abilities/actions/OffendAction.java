package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

public class OffendAction extends Ability {
    public static final String TYPE = "обижать";

    public OffendAction() {
        super(TYPE);
    }

    @Override
    public String perform() {
        return "обижать";
    }

    @Override
    public String performOn(PhysicalObject object) {
        return "%s %s".formatted(perform(), object.getPhysicalObjectName());
    }
}
