package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.AbilityResult;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;

import java.util.Objects;

public class GoAction extends Ability {
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

    public GoAction setPlace(Place place) {
        this.place = place;
        return this;
    }

    @Override
    public AbilityResult execute(Human self) {
        self.setPosition(place.getPosition());
        return new AbilityResult(describe());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        GoAction that = (GoAction) o;

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
