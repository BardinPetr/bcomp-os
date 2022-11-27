package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IAlteringModifier;

import java.util.Objects;

public class GoalModifier implements IAlteringModifier {
    private Describable goal;

    public GoalModifier(String goal) {
        this.goal = () -> goal;
    }

    public GoalModifier(Describable goal) {
        this.goal = goal;
    }

    @Override
    public String getType() {
        return "с целью";
    }

    @Override
    public String getValue() {
        return getGoal();
    }

    @Override
    public void setValue(Object value) {
        goal = (Describable) value;
    }

    public String getGoal() {
        return goal.describe();
    }

    public void setGoal(Describable goal) {
        this.goal = goal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalModifier that = (GoalModifier) o;

        return Objects.equals(goal, that.goal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goal);
    }

    @Override
    public String toString() {
        return "GoalModifier{reason='%s'}".formatted(goal);
    }
}
