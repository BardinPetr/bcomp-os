package ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces;

import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;

public interface Scriptable {
    @Deprecated
    String getScenario();

    @Deprecated
    void addScenario(Scenario scenario);
}
