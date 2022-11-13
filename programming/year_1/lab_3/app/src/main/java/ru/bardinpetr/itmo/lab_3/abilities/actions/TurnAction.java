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
    public String getVerb() {
        return "поворачивать";
    }

    @Override
    protected String getDescription() {
        return dir;
    }
}
