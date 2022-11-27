package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;

import java.util.Objects;

public class NameModifier implements IModifier {

    private final String name;

    public NameModifier(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "имя";
    }

    @Override
    public String getValue() {
        return name;
    }

    @Override
    public String toString() {
        return "NameModifier{name='%s'}".formatted(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NameModifier that = (NameModifier) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
