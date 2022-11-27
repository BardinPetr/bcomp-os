package ru.bardinpetr.itmo.lab_4.realitylib.scenarios;

import ru.bardinpetr.itmo.lab_4.story.modifiers.models.LogicOperator;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;

public abstract class Scenario {

    public abstract Scenario addIf(LogicOperator operator, String text);

    public Scenario addIf(String text) {
        return addIf(LogicOperator.AND, text);
    }

    public Scenario addIf(IScenarioAction action) {
        return addIf(action.describe());
    }

    public abstract Scenario addThen(LogicOperator operator, IScenarioAction action);

    public Scenario addThen(IScenarioAction action) {
        return addThen(LogicOperator.AND, action);
    }

    public abstract Scenario addElse();

    public abstract void append(Scenario other);

    public abstract String print();
}
