package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

public class HasModifier implements IModifier {
    private final PhysicalObject thing;

    public HasModifier(PhysicalObject thing) {
        this.thing = thing;
    }

    public String getType() {
        return "имеет";
    }

    @Override
    public String getValue() {
        return thing.describe();
    }
}
