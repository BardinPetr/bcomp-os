package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

import java.util.Objects;

public class SayAction extends Ability {
    public static final String TYPE = "говорить";

    private final String text;

    public SayAction(String text) {
        super(TYPE);
        this.text = text;
    }

    @Override
    public String getVerb() {
        return "говорит";
    }

    @Override
    protected String getDescription() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SayAction sayAction = (SayAction) o;

        return Objects.equals(text, sayAction.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }

    @Override
    public String toString() {
        return "SayAction{text='%s'} %s".formatted(text, super.toString());
    }
}

