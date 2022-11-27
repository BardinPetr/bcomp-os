package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IAlteringModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;

import java.util.Objects;

public class PlaceModifier implements IAlteringModifier {

    private Place place;
    private PlaceRelation relation = null;

    public PlaceModifier() {}
    public PlaceModifier(Place place) {
        this.place = place;
    }

    public PlaceModifier(PlaceRelation relation, Place place) {
        this.relation = relation;
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public void setValue(Object value) {
        place = (Place) value;
    }

    public PlaceRelation getRelation() {
        return relation;
    }

    public void setRelation(PlaceRelation relation) {
        this.relation = relation;
    }

    @Override
    public String getType() {
        return (relation == null ? "" : "%s ".formatted(relation.name())) + "место";
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

    public enum PlaceRelation {
        IN,
        ON,
        OVER,
        UNDER,
        NEXT_TO,
        NEAR,
        IN_BEHIND
    }
}
