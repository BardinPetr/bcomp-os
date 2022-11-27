package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;

public class SongAction extends Ability {
    public static final String TYPE = "писать песни";

    public SongAction() {
        super(TYPE);
    }

    @Override
    public String getVerb() {
        return "писать песни";
    }

    @Override
    public String toString() {
        return "SongAction{} %s".formatted(super.toString());
    }
}
