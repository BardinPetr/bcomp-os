package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;

import java.util.Objects;

public class HideAction extends Ability {
    private Place place;

    public HideAction(Place place) {
        this.place = place;
    }

    public HideAction() {
    }

    public Place getPlace() {
        return place;
    }

    public HideAction setPlace(Place place) {
        this.place = place;
        return this;
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
