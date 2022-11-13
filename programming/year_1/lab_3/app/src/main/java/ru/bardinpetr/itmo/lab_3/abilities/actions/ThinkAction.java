package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Tool;

public class ThinkAction extends Ability {
    public static final String TYPE = "думать";

    private final String text;

    public ThinkAction(String text) {
        super(TYPE);
        this.text = text;
    }

    @Override
    public String perform() {
        return "думать о %s".formatted(text);
    }
}
