package ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces;

import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;

public interface Scriptable {
    String getScenario();

    void addScenario(Scenario scenario);
}
