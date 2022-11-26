package ru.bardinpetr.itmo.lab_4.properties.modifiers;

import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_4.things.place.Place;

import java.util.Objects;

public class PlaceModifier implements IModifier {

    private Place place;

    public PlaceModifier(Place place) {
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String getType() {
        return "место";
    }

    @Override
    public String getValue() {
        return place.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlaceModifier that = (PlaceModifier) o;

        return Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return place != null ? place.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlaceModifier{place=%s}".formatted(place.describe());
    }
}
