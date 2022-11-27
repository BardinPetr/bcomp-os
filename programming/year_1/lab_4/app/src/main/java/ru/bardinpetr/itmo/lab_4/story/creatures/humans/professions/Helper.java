package ru.bardinpetr.itmo.lab_4.story.creatures.humans.professions;

import ru.bardinpetr.itmo.lab_4.story.actions.HelpAction;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.ProfessionHuman;

public class Helper extends ProfessionHuman {

    public Helper(String name, String patronymic, String surname) {
        super(name, patronymic, surname, new HelpAction());
    }

    public Helper(String name) {
        this(name, "", "");
    }

    public void setMaster(Human master) {
        ((HelpAction) getProfessionalAbility())
                .setMaster(master);
    }

    @Override
    public String getProfessionName() {
        return "помощник";
    }

    @Override
    public String toString() {
        return "Helper{%s}".formatted(super.toString());
    }
}
