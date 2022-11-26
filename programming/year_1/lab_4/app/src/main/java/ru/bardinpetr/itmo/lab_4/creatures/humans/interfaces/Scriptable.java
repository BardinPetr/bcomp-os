package ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces;

import ru.bardinpetr.itmo.lab_4.scenarios.Scenario;

public interface Scriptable {
    String getScenario();

    void addScenario(Scenario scenario);
}
