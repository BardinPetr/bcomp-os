package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.place.Place;

public class LiveAction extends Ability {
    public static final String TYPE = "LIVE";

    private Place place;

    public LiveAction(Place place) {
        super(TYPE);
        this.place = place;
    }

    @Override
    public String perform() {
        return "живет в %s".formatted(place.getName());
    }

    @Override
    public String performOn(PhysicalObject object) {
        return "заставляет %s жить в %s".formatted(object.getPhysicalObjectName(), place.getName());
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
