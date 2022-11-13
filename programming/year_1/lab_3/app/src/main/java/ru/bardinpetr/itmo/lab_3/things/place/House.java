package ru.bardinpetr.itmo.lab_3.things.place;

import java.util.Objects;

public class House extends Place {
    private final int maxPeople;

    public House(String name, double[] coordinates, int maxPeople) {
        super(name, coordinates);
        this.maxPeople = maxPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        House house = (House) o;

        return maxPeople == house.maxPeople;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxPeople);
    }

    @Override
    public String toString() {
        return "House{maxPeople=%d, %s}".formatted(maxPeople, super.toString());
    }
}
