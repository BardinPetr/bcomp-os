package ru.bardinpetr.itmo.lab_3.abilities.interfaces;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

import java.util.List;

public interface IAble {
    void addAbility(Ability ability);

    List<Ability> getAbilities();

    default Ability getAbility(String type) {
        List<Ability> abilities = getAbilities();
        for (int i = 0; i < abilities.size(); i++)
            if (abilities.get(i).getAbilityType().equals(type)) return abilities.get(i);
        return null;
    }

    default Ability getAbilityByName(String name) {
        List<Ability> abilities = getAbilities();
        for (int i = 0; i < abilities.size(); i++)
            if (abilities.get(i).getAbilityName().equals(name)) return abilities.get(i);
        return null;
    }
}
