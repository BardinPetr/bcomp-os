package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;

import java.util.Objects;

public class MaterialModifier implements IModifier {
    private Thing material;

    public String getType() {
        return "из";
    }

    @Override
    public String getValue() {
        return material.describe();
    }

    public Thing getMaterial() {
        return material;
    }

    public void setMaterial(Thing material) {
        this.material = material;
    }
}
