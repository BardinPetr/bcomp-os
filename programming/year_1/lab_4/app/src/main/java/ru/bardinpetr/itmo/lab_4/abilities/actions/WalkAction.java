package ru.bardinpetr.itmo.lab_4.abilities.actions;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.abilities.AbilityResult;
import ru.bardinpetr.itmo.lab_4.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.things.place.Place;

import java.util.Objects;

public class WalkAction extends Ability {
    private Place place;
    @Override
    public String getVerb() {
        return "перемещаться";
    }

    @Override
    protected String getDescription() {
        return place.getName();
    }

    public Place getPlace() {
        return place;
    }

    public WalkAction setPlace(Place place) {
        this.place = place;
        return this;
    }

    @Override
    public AbilityResult execute(Human self) {
        self.setPosition(place.getPosition());
        return new AbilityResult("успешно");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WalkAction that = (WalkAction) o;

        return Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), place);
    }

    @Override
    public String toString() {
        return "WalkAction{place=%s} %s".formatted(place, super.toString());
    }
}
