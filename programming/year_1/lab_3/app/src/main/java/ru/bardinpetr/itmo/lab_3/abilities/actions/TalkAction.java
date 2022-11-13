package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

public class TalkAction extends Ability {
    public static final String TYPE = "болтать";

    public TalkAction() {
        super(TYPE);
    }

    @Override
    public String perform() {
        return "болтает";
    }

    @Override
    public String performOn(PhysicalObject object) {
        return "болтает с %s".formatted(object.getPhysicalObjectName());
    }
}
