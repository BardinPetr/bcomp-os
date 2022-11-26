package ru.bardinpetr.itmo.lab_4.things;

import java.util.Objects;

public class Thing extends PhysicalObject {
    private final String name;

    public Thing(String name) {
        this.name = name;
    }

    @Override
    public String getPhysicalObjectName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Thing thing = (Thing) o;

        return Objects.equals(name, thing.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Thing{name='%s'} %s".formatted(name, super.toString());
    }
}
