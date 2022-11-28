package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;

import java.util.Objects;

public class SayAction extends Ability {
    private String text = "";

    public SayAction(String text) {
        this.text = text;
    }

    public SayAction() {
    }

    public SayAction setText(String text) {
        this.text = text;
        return this;
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
        return "SayAction{resultText='%s'} %s".formatted(text, super.toString());
    }
}

