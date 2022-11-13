package ru.bardinpetr.itmo.lab_3.things.wear;

import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

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
}
