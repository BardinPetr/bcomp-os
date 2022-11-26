package ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces;

import ru.bardinpetr.itmo.lab_4.abilities.interfaces.IAble;
import ru.bardinpetr.itmo.lab_4.creatures.interfaces.Nameable;
import ru.bardinpetr.itmo.lab_4.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.things.tool.Tool;

public interface IPerforming extends IAble, Nameable {
    default IScenarioAction performByName(String name) {
        return () -> "%s %s".formatted(getName(), getAbilityByName(name).perform());
    }

    default IScenarioAction performByType(String type) {
        return () -> "%s %s".formatted(getName(), getAbility(type).perform());
    }

    default IScenarioAction performByNameWithOn(String name, Tool tool, PhysicalObject object) {
        return () -> "%s %s".formatted(getName(), getAbilityByName(name).performWithOn(tool, object));
    }

    default IScenarioAction performByTypeWithOn(String type, Tool tool, PhysicalObject object) {
        return () -> "%s %s".formatted(getName(), getAbility(type).performWithOn(tool, object));
    }
}
