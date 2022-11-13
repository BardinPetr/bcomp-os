package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class SongAction extends Ability {
    public static final String TYPE = "писать песни";

    public SongAction() {
        super(TYPE);
    }

    @Override
    public String getVerb() {
        return "писать песни";
    }
}
