package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;

import java.util.Objects;

public class TurnAction extends Ability {
    private String dir;

    public TurnAction(String dir) {
        this.dir = dir;
    }

    public TurnAction() {
    }

    public String getDir() {
        return dir;
    }

    public TurnAction setDir(String dir) {
        this.dir = dir;
        return this;
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
