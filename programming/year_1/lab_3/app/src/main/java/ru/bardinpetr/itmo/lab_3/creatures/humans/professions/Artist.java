package ru.bardinpetr.itmo.lab_3.creatures.humans.professions;

import ru.bardinpetr.itmo.lab_3.abilities.actions.DoMechanicsAction;
import ru.bardinpetr.itmo.lab_3.creatures.humans.ProfessionHuman;

public class Artist extends ProfessionHuman {

    public Artist(String name, String patronymic, String surname) {
        super(name, patronymic, surname, new DoMechanicsAction());
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
}
