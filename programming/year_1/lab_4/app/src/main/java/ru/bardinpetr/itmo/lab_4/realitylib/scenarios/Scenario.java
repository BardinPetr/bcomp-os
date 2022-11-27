package ru.bardinpetr.itmo.lab_4.realitylib.scenarios;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.interfaces.Nameable;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.LogicOperator;

public abstract class Scenario implements Describable, Nameable {

    private String name = "";


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

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scenario newSentence() {
        return this;
    }

    @Override
    public String describe() {
        return "сценарий \"%s\": \n%s\n".formatted(getName(), print());
    }
}
