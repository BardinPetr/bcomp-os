package ru.bardinpetr.itmo.lab_4.scenarios.interfaces;

import ru.bardinpetr.itmo.lab_4.scenarios.Scenario;

public interface Scriptable {
    String getScenario();

    void addScenario(Scenario scenario);
}
