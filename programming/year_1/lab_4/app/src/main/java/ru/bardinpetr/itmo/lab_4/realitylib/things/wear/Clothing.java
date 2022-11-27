package ru.bardinpetr.itmo.lab_4.realitylib.things.wear;

import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

import java.util.Objects;

public class Clothing extends PhysicalObject {
    private final WearType type;
    private final int size;

    public Clothing(WearType type, int size) {
        this.type = type;
        this.size = size;
    }

    public WearType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public String wear() {
        return "";
    }

    @Override
    public String getPhysicalObjectName() {
        return getType().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Clothing clothing = (Clothing) o;

        if (size != clothing.size) return false;
        return type == clothing.type;
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size, type);
    }

    @Override
    public String toString() {
        return "Clothing{type=%s, size=%d} %s".formatted(type, size, super.toString());
    }
}
