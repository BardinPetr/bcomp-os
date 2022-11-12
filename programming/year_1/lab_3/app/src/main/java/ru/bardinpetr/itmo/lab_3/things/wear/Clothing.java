package ru.bardinpetr.itmo.lab_3.things.wear;

import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

public class Clothing extends PhysicalObject implements Wearable {
    private final WearType type;
    private final int size;

    public Clothing(WearType type, int size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public WearType getType() {
        return type;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void wear() {

    }

    @Override
    public String describe() {
        return null;
    }
}
