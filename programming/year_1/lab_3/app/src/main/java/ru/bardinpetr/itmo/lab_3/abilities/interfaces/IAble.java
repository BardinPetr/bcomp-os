package ru.bardinpetr.itmo.lab_3.abilities.interfaces;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

import java.util.List;

public interface IAble {
    void addAbility(Ability ability);

    List<Ability> getAbilities();

    default Ability getAbility(String type) {
        for (Ability ability : getAbilities())
            if (ability.getAbilityType().equals(type)) return ability;
        return null;
    }

    default Ability getAbilityByName(String name) {
        for (Ability ability : getAbilities())
            if (ability.getAbilityName().equals(name)) return ability;
        return null;
    }
}
