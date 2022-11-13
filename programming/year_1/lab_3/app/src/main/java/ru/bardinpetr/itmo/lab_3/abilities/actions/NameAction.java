package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

public class NameAction extends SayAction {
    public static final String TYPE = "называть";

    private final String text;

    public NameAction(String text) {
        super(TYPE);
        this.text = text;
    }

    @Override
    public String perform() {
        return "называть %s".formatted(text);
    }

    @Override
    public String performOn(PhysicalObject object) {
        return "называть %s %s".formatted(object.getPhysicalObjectName(), text);
    }
}
