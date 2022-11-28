package ru.bardinpetr.itmo.lab_4.realitylib.scenarios;

import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.LogicOperator;

import java.util.ArrayList;
import java.util.List;

public class ExecutableScenario extends Scenario {

    private final List<IScenarioAction> conditions = new ArrayList<>();
    private final List<IScenarioAction> actions = new ArrayList<>();
    private final List<IScenarioAction> elseActions = new ArrayList<>();
    private boolean isElse = false;

    public ExecutableScenario(String name) {
        setName(name);
    }

    @Override
    public Scenario addIf(LogicOperator operator, String text) {
        return addIf((ctx) -> text);
    }

    @Override
    public Scenario addIf(String text) {
        return addIf((ctx) -> text);
    }

    @Override
    public Scenario addIf(IScenarioAction action) {
        conditions.add(action);
        return this;
    }

    @Override
    public Scenario addThen(LogicOperator operator, IScenarioAction action) {
        (isElse ? elseActions : actions).add(action);
        return this;
    }

    @Override
    public Scenario addThen(IScenarioAction action) {
        return addThen(LogicOperator.AND, action);
    }

    @Override
    public Scenario addElse() {
        isElse = true;
        return null;
    }

    @Override
    public Scenario newSentence() {
        return this;
    }

    public void append(Scenario other) {
    }

    public String print() {
        StoryContext context = new StoryContext();
        StringBuilder sb = new StringBuilder("ЕСЛИ: ");
        for (var i : conditions) sb.append(i.describe()).append(", ");
        sb.append("; ТО: ");
        for (var i : actions) sb.append(i.execute(context)).append(", ");
        sb.append("; ИНАЧЕ: ");
        for (var i : elseActions) sb.append(i.execute(context)).append(", ");
        return sb.toString();
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
