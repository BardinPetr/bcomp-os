package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TargetedAbility;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.QuarrelState;

import java.util.Objects;

public class QuarrelAction extends TargetedAbility {
    private QuarrelState state = QuarrelState.NORMAL;

    public QuarrelAction(QuarrelState state) {
        this.state = state;
    }

    public QuarrelAction() {
    }

    public QuarrelState getState() {
        return state;
    }

    public QuarrelAction setState(QuarrelState state) {
        this.state = state;
        return this;
    }

    @Override
    public String getVerb() {
        return (state == QuarrelState.IN_QUARREL ? "ссориться" : "мириться");
    }

    @Override
    protected String getObjectPreposition() {
        return "с";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        QuarrelAction that = (QuarrelAction) o;

        return state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), state);
    }

    @Override
    public String toString() {
        return "QuarrelAction{state=%s} %s".formatted(state, super.toString());
    }
}
