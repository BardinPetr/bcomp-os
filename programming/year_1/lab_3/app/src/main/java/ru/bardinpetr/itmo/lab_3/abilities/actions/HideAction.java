package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.place.Place;

public class HideAction extends Ability {
    public static final String TYPE = "прятаться";

    private final Place place;

    public HideAction(Place place) {
        super(TYPE);
        this.place = place;
    }

    @Override
    public String perform() {
        return "прятаться %s %s".formatted(describeMods(), place.getName());
    }
}
