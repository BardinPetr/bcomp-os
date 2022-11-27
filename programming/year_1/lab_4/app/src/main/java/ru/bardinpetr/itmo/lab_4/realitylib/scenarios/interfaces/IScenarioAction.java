package ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;

public interface IScenarioAction extends Describable {
    String execute();

    @Override
    default String describe() {
        return execute();
    }
}