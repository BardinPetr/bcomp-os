package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.properties.models.QuarrelState;

import java.util.Objects;

public class QuarrelAction extends Ability {
    public static final String TYPE = "ссориться";

    private final QuarrelState state;

    public QuarrelAction(QuarrelState state) {
        super(TYPE);
        this.state = state;
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
