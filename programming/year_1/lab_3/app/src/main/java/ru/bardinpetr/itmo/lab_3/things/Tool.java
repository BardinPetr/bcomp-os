package ru.bardinpetr.itmo.lab_3.things;

public abstract class Tool extends Thing {
    public Tool(String name) {
        super(name);
    }

    public abstract String apply(PhysicalObject target);
}
