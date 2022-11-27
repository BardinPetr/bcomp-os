package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;

import java.util.Objects;

public class TurnAction extends Ability {
    public static final String TYPE = "поворачивать";

    private final String dir;

    public TurnAction(String dir) {
        super(TYPE);
        this.dir = dir;
    }

    @Override
    public String getVerb() {
        return "поворачивать";
    }

    @Override
    protected String getDescription() {
        return dir;
    }

    @Override
    public String toString() {
        return "TurnAction{dir='%s'} %s".formatted(dir, super.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TurnAction that = (TurnAction) o;

        return Objects.equals(dir, that.dir);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dir);
    }
}
