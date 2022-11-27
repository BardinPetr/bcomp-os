package ru.bardinpetr.itmo.lab_4.realitylib.scenarios;

import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.LogicOperator;

import java.util.ArrayList;
import java.util.List;

public class ExecutableScenario extends Scenario {

    private final List<IScenarioAction> conditions = new ArrayList<>();
//    private final List<IScenarioAction> conditions = new ArrayList<>();

    public ExecutableScenario addIf(LogicOperator operator, String text) {
        return this;
    }

    public ExecutableScenario addThen(LogicOperator operator, IScenarioAction action) {

        return this;
    }

    public ExecutableScenario addElse() {

        return this;
    }

    public ExecutableScenario newSentence() {

        return this;
    }

    public void append(Scenario other) {

    }

    public String print() {
        return "";
    }

    @Override
    public String toString() {
        return "Scenario{scenarioText=%s}".formatted(print());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExecutableScenario scenario1 = (ExecutableScenario) o;

        return print().equals(scenario1.print());
    }

    @Override
    public int hashCode() {
        return print().hashCode();
    }
}
