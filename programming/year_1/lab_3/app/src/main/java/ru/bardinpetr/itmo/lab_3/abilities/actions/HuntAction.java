package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Tool;
import ru.bardinpetr.itmo.lab_3.things.Weapon;

public class HuntAction extends Ability {
    public static final String TYPE = "охотиться";

    public HuntAction() {
        super(TYPE);
    }

    @Override
    public String perform() {
        return "охотиться";
    }

    @Override
    public String performWithOn(Tool tool, PhysicalObject object) {
        return "охотиться %s".formatted(tool.apply(object));
    }
}
