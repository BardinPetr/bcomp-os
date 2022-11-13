package ru.bardinpetr.itmo.lab_3.creatures.humans.professions;

import ru.bardinpetr.itmo.lab_3.abilities.actions.HuntAction;
import ru.bardinpetr.itmo.lab_3.creatures.humans.ProfessionHuman;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Weapon;

public class Hunter extends ProfessionHuman {
    private final Weapon mainWeapon;

    public Hunter(String name, String patronymic, String surname, Weapon mainWeapon) {
        super(name, patronymic, surname, new HuntAction());
        this.mainWeapon = mainWeapon;
        have(mainWeapon);
    }

    public Hunter(String name, Weapon mainWeapon) {
        this(name, "", "", mainWeapon);
    }

    public String goHunt(PhysicalObject object) {
        HuntAction action = (HuntAction) getProfessionalAbility();
        return action.performOnWith(mainWeapon, object);
    }

    @Override
    public String getProfessionName() {
        return "охотник";
    }
}
