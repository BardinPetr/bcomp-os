package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_3.properties.models.Color;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;

import java.util.List;

public class HasModifier implements IModifier {
    public static final String TYPE = "has";
    private final PhysicalObject thing;

    public HasModifier(PhysicalObject thing) {
        this.thing = thing;
    }

    @Override
    public String getPreposition() {
        return "имеет";
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getValue() {
        return thing.describe();
    }
}
