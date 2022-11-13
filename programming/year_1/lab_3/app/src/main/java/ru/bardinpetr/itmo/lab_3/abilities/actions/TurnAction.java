package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class TurnAction extends Ability {
    public static final String TYPE = "поворачивать";

    private final String dir;

    public TurnAction(String dir) {
        super(TYPE);
        this.dir = dir;
    }

    @Override
    public String perform() {
        return "поворачивать %s %s".formatted(describeMods(), dir);
    }
}
