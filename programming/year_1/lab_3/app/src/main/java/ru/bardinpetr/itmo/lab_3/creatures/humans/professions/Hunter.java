package ru.bardinpetr.itmo.lab_3.creatures.humans.professions;

import ru.bardinpetr.itmo.lab_3.abilities.actions.HuntAction;
import ru.bardinpetr.itmo.lab_3.creatures.humans.ProfessionHuman;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.tool.Weapon;

import java.util.Objects;

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
        return action.performWithOn(mainWeapon, object);
    }

    @Override
    public String getProfessionName() {
        return "охотник";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hunter hunter = (Hunter) o;

        return Objects.equals(mainWeapon, hunter.mainWeapon);
    }

    @Override
    public int hashCode() {
        return mainWeapon != null ? mainWeapon.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Hunter{mainWeapon=%s, %s}".formatted(mainWeapon, super.toString());
    }


}
