package ru.bardinpetr.itmo.lab_3.human.professions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.abilities.actions.cure.CureAction;
import ru.bardinpetr.itmo.lab_3.human.LivingHuman;
import ru.bardinpetr.itmo.lab_3.human.interfaces.Professionable;

public class Doctor extends LivingHuman implements Professionable {
    private final Ability professionalAbility = new CureAction();

    public Doctor(String name, String patronymic, String surname) {
        super(name, patronymic, surname);
        addAbility(professionalAbility);
    }

    public Doctor(String name) {
        this(name, "", "");
    }

    @Override
    public Ability getProfessionalAbility() {
        return professionalAbility;
    }
}
