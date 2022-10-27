package ru.bardinpetr.itmo.lab_3.abilities.interfaces;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

import java.util.List;

public interface Able {
    void addAbility(Ability ability);

    List<Ability> getAbilities();
}
