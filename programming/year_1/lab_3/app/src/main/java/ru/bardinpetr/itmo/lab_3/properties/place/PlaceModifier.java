package ru.bardinpetr.itmo.lab_3.properties.place;

import ru.bardinpetr.itmo.lab_3.properties.Modifier;

public class PlaceModifier extends Modifier {
    private final Place place;

    public PlaceModifier(Place place) {
        this.place = place;
    }

    @Override
    public String getPreposition() {
        return "в";
    }

    @Override
    public String getType() {
        return "местоположение";
    }

    @Override
    public String getValue() {
        return place.name();
    }
}
