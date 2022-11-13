package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class MostOfModifier implements IModifier {
    private final IModifier modifier;
    private final HumanGroup group;

    public MostOfModifier(IModifier modifier, HumanGroup group) {
        this.modifier = modifier;
        this.group = group;
    }

    public String getType() {
        return "наиболее";
    }

    @Override
    public String getValue() {
        return "%s из группы %s".formatted(modifier.getValue(), group.getName());
    }
}
