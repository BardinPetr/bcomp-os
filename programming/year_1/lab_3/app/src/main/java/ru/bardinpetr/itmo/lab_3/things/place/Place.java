package ru.bardinpetr.itmo.lab_3.things.place;

import java.util.Arrays;
import java.util.Objects;

public class Place {
    private final String name;
    private final double[] coordinates;

    public Place(String name, double[] coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Place{name='%s', coordinates=%s}".formatted(name, Arrays.toString(coordinates));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;

        if (!Objects.equals(name, place.name)) return false;
        return Arrays.equals(coordinates, place.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(coordinates));
    }
}
