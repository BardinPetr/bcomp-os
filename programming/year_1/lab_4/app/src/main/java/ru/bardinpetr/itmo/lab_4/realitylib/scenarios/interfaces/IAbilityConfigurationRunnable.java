package ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.StoryContext;

public interface IAbilityConfigurationRunnable {
    Ability configure(Ability ability, StoryContext ctx);
}
