package ru.bardinpetr.itmo.lab_4.abilities.actions;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.things.place.Place;

import java.util.Objects;

public class HideAction extends Ability {
    public static final String TYPE = "прятаться";
    private final Place place;

    public HideAction(Place place) {
        super(TYPE);
        this.place = place;
    }

    @Override
    public String getVerb() {
        return "прятаться";
    }

    @Override
    public String getDescription() {
        return place.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HideAction that = (HideAction) o;

        return Objects.equals(place, that.place);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), place);
    }

    @Override
    public String toString() {
        return "HideAction{place=%s} %s".formatted(place, super.toString());
    }
}
