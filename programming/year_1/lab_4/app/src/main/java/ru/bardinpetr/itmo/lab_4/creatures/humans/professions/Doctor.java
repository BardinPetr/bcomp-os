package ru.bardinpetr.itmo.lab_4.creatures.humans.professions;

import ru.bardinpetr.itmo.lab_4.abilities.actions.CureAction;
import ru.bardinpetr.itmo.lab_4.creatures.humans.ProfessionHuman;

public class Doctor extends ProfessionHuman {
    public Doctor(String name, String patronymic, String surname) {
        super(name, patronymic, surname, new CureAction());
    }

    public Doctor(String name) {
        this(name, "", "");
    }

    @Override
    public String getProfessionName() {
        return "доктор";
    }

    @Override
    public String toString() {
        return "Doctor{%s}".formatted(super.toString());
    }
}
