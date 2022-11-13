package ru.bardinpetr.itmo.lab_3.things;

public class Thing extends PhysicalObject{
    private final String name;

    public Thing(String name) {
        this.name = name;
    }

    @Override
    public String getPhysicalObjectName() {
        return name;
    }
}
