package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.place.Place;

public class WalkAction extends Ability {
    public static final String TYPE = "перемещаться";

    private final Place place;

    public WalkAction(Place place) {
        super(TYPE);
        this.place = place;
    }

    @Override
    public String perform() {
        return "перемещаться %s".formatted(place.getName());
    }
}
