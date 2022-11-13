package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_3.things.place.Place;

public class PlaceModifier implements IModifier {

    public static final String TYPE = "place";

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
    public String getPreposition() {
        return "в";
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getValue() {
        return place.getName();
    }
}
