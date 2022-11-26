package ru.bardinpetr.itmo.lab_4.abilities.actions;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.things.place.Place;

import java.util.Objects;

public class WalkAction extends Ability {
    public static final String TYPE = "перемещаться";

    private final Place place;

    public WalkAction(Place place) {
        super(TYPE);
        this.place = place;
    }

    @Override
    public String getVerb() {
        return "перемещаться";
    }

    @Override
    protected String getDescription() {
        return place.getName();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WalkAction that = (WalkAction) o;

        return Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), place);
    }

    @Override
    public String toString() {
        return "WalkAction{place=%s} %s".formatted(place, super.toString());
    }
}
