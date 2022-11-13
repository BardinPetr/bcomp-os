package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_3.things.place.Place;

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
}
