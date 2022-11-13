package ru.bardinpetr.itmo.lab_3.creatures.humans.professions;

import ru.bardinpetr.itmo.lab_3.abilities.actions.DoMechanicsAction;
import ru.bardinpetr.itmo.lab_3.abilities.actions.SongAction;
import ru.bardinpetr.itmo.lab_3.creatures.humans.ProfessionHuman;

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

    public String draw() {
        return "музыка написана и сыграна";
    }
}
