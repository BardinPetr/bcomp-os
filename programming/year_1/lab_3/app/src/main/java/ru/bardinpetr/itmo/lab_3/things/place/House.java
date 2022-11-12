package ru.bardinpetr.itmo.lab_3.things.place;

public class House extends Place {
    private final int maxPeople;

    public House(String name, double[] coordinates, int maxPeople) {
        super(name, coordinates);
        this.maxPeople = maxPeople;
    }

    public int getMaxPeople() {
        return maxPeople;
    }
}
