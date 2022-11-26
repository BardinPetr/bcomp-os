package ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.abilities.interfaces.IAble;
import ru.bardinpetr.itmo.lab_4.creatures.interfaces.Nameable;
import ru.bardinpetr.itmo.lab_4.scenarios.interfaces.IAbilityConfigurationRunnable;
import ru.bardinpetr.itmo.lab_4.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.things.tool.Tool;

public interface IPerforming extends IAble, Nameable {

//    <T extends Ability> IScenarioAction perform(T ability, IAbilityConfigurationRunnable conf);
    IScenarioAction perform(Ability ability, IAbilityConfigurationRunnable conf);

    default IScenarioAction perform(String abilityName, IAbilityConfigurationRunnable conf) {
        return perform(getAbilityByName(abilityName), conf);
    }

    default IScenarioAction perform(Class abilityClass, IAbilityConfigurationRunnable conf) {
        return perform(getAbility(abilityClass), conf);
    }

    @Deprecated
    default IScenarioAction performByName(String name) {
        return () -> "%s %s".formatted(getName(), getAbilityByName(name).perform());
    }

    @Deprecated
    default IScenarioAction performByType(String type) {
        return () -> "%s %s".formatted(getName(), getAbility(type).perform());
    }

    @Deprecated
    default IScenarioAction performByNameWithOn(String name, Tool tool, PhysicalObject object) {
        return () -> "%s %s".formatted(getName(), getAbilityByName(name).performWithOn(tool, object));
    }

    @Deprecated
    default IScenarioAction performByTypeWithOn(String type, Tool tool, PhysicalObject object) {
        return () -> "%s %s".formatted(getName(), getAbility(type).performWithOn(tool, object));
    }
}
