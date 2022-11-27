package ru.bardinpetr.itmo.lab_4.abilities.actions;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;

import java.util.Objects;

public class ThinkAction extends Ability {
    public static final String TYPE = "думать";

    private final String text;

    public ThinkAction(String text) {
        super(TYPE);
        this.text = text;
    }

    @Override
    public String getVerb() {
        return "думать";
    }

    @Override
    protected String getDescription() {
        return "о %s".formatted(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ThinkAction that = (ThinkAction) o;

        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), text);
    }

    @Override
    public String toString() {
        return "ThinkAction{resultText='%s'} %s".formatted(text, super.toString());
    }
}
