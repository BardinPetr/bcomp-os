package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.place.Place;

public class LiveAction extends Ability {
    public static final String TYPE = "LIVE";
    private Place place;

    public LiveAction(Place place) {
        super(TYPE);
        this.place = place;
    }

    @Override
    public String getVerb() {
        return "живет";
    }

    @Override
    public String getDescription() {
        return "в %s".formatted(place.getName());
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
