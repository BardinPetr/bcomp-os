package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Tool;

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
    public String performWithOn(Tool tool, PhysicalObject object) {
        return "болтает с %s".formatted(object.getPhysicalObjectName());
    }
}
