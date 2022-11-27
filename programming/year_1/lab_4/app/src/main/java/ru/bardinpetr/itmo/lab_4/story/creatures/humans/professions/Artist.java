package ru.bardinpetr.itmo.lab_4.story.creatures.humans.professions;

import ru.bardinpetr.itmo.lab_4.story.actions.DrawAction;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.ProfessionHuman;

public class Artist extends ProfessionHuman {

    public Artist(String name, String patronymic, String surname) {
        super(name, patronymic, surname, new DrawAction());
    }

    public Artist(String name) {
        this(name, "", "");
    }

    @Override
    public String getProfessionName() {
        return "художник";
    }

    public String draw() {
        return "картина нарисована";
    }

    @Override
    public String toString() {
        return "Artist{%s}".formatted(super.toString());
    }
}
