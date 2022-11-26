package ru.bardinpetr.itmo.lab_4.scenarios;

import ru.bardinpetr.itmo.lab_4.properties.models.LogicOperator;
import ru.bardinpetr.itmo.lab_4.scenarios.interfaces.IScenarioAction;

public class Scenario {

    private final StringBuilder scenario = new StringBuilder();
    private int sentenceCount = 0;
    private int partsCount = 0;
    private boolean isIfSectionActive = true;

    public Scenario addIf(IScenarioAction action) {
        return addIf(action.perform());
    }

    public Scenario addIf(String text) {
        return addIf(LogicOperator.AND, text);
    }

    public Scenario addIf(LogicOperator operator, String text) {
        if (sentenceCount == 0 || !isIfSectionActive) return this;

        String pre = partsCount == 0 ? "" : ", %s".formatted(operator);
        scenario.append(" %s ЕСЛИ %s".formatted(pre, text));

        partsCount++;
        return this;
    }

    public Scenario addThen(IScenarioAction action) {
        return addThen(LogicOperator.AND, action);
    }

    public Scenario addThen(LogicOperator operator, IScenarioAction action) {
        if (sentenceCount == 0) return this;

        if (isIfSectionActive) {
            if (partsCount > 0) scenario.append(";  ТО делать: ");
            partsCount = 0;
        }

        String pre = partsCount == 0 ? "" : ", %s".formatted(operator);
        scenario.append(" %s %s".formatted(pre, action.perform()));

        isIfSectionActive = false;
        partsCount++;
        return this;
    }

    public Scenario addElse() {
        if (sentenceCount == 0 || isIfSectionActive) return this;
        partsCount = 0;
        scenario.append("; ИНАЧЕ делать: ");
        return this;
    }

    public Scenario newSentence() {
        if (sentenceCount > 0 && isIfSectionActive) return this;
        isIfSectionActive = true;
        partsCount = 0;
        if (sentenceCount > 0) scenario.append("\n  ");
        sentenceCount++;
        scenario.append("%d.) ".formatted(sentenceCount));
        return this;
    }

    public void append(Scenario other) {
        scenario.append("\nгруппа сценариев:\n");
        scenario.append(other.print());
    }

    public String print() {
        return scenario.toString();
    }

    @Override
    public String toString() {
        return "Scenario{scenarioText=%s}".formatted(print());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scenario scenario1 = (Scenario) o;

        return print().equals(scenario1.print());
    }

    @Override
    public int hashCode() {
        return print().hashCode();
    }
}
