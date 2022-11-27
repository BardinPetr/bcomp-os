package ru.bardinpetr.itmo.lab_4.story.creatures.humans.professions;

import ru.bardinpetr.itmo.lab_4.story.actions.SongAction;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.ProfessionHuman;

public class Musician extends ProfessionHuman {

    public Musician(String name, String patronymic, String surname) {
        super(name, patronymic, surname, new SongAction());
    }

    public Musician(String name) {
        this(name, "", "");
    }

    @Override
    public String getProfessionName() {
        return "музыкант";
    }

    public String play() {
        return "музыка написана и сыграна";
    }

    @Override
    public String toString() {
        return "Musician{%s}".formatted(super.toString());
    }
}
