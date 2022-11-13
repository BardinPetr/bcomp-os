package ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces;

import ru.bardinpetr.itmo.lab_3.scenarios.Scenario;

public interface IScriptable {
    String getScenario();

    void addScenario(Scenario scenario);
}
