package ru.bardinpetr.itmo.lab_4.things.tool;

import ru.bardinpetr.itmo.lab_4.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.things.Thing;

public abstract class Tool extends Thing {
    public Tool(String name) {
        super(name);
    }

    public abstract String apply(PhysicalObject target);

    @Override
    public String toString() {
        return "Tool{%s}".formatted(super.toString());
    }
}
