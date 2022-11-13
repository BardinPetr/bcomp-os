package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Tool;

public class NameAction extends Ability {
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
    public String performWithOn(Tool tool, PhysicalObject object) {
        return "называть %s %s".formatted(object.getPhysicalObjectName(), text);
    }
}
