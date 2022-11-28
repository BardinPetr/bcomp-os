package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IAlteringModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;

import java.util.Objects;

public class NameModifier implements IAlteringModifier {

    private String name;

    public NameModifier(String name) {
        this.name = name;
    }

    public NameModifier() {
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
    public void setValue(Object value) {
        name = (String) value;
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
