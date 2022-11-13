package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MostOfModifier that = (MostOfModifier) o;

        if (!Objects.equals(modifier, that.modifier)) return false;
        return Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modifier, group);
    }

    @Override
    public String toString() {
        return "MostOfModifier{modifier=%s, group=%s}".formatted(modifier, group);
    }
}
