package ru.bardinpetr.itmo.lab_4.scenarios.interfaces;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.properties.interfaces.Modifiable;
import ru.bardinpetr.itmo.lab_4.scenarios.StoryContext;

public interface IAbilityConfigurationRunnable {
    Modifiable configure(Ability ability, StoryContext ctx);
}
