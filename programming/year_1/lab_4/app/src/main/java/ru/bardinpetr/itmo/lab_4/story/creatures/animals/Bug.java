package ru.bardinpetr.itmo.lab_4.story.creatures.animals;

import ru.bardinpetr.itmo.lab_4.realitylib.creatures.animals.Animal;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;

public class Bug extends Animal {
    public Bug(String name) {
        super("жук%s".formatted(name));
    }

    @Override
    public IScenarioAction perform() {
        return (context) -> "%s лететь".formatted(getName());
    }
}
