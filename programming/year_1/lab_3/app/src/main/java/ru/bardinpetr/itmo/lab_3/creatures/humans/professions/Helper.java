package ru.bardinpetr.itmo.lab_3.creatures.humans.professions;

import ru.bardinpetr.itmo.lab_3.abilities.actions.HelpAction;
import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_3.creatures.humans.ProfessionHuman;

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
}
