package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;

public class CountModifier implements IModifier {
    private final int count;

    public CountModifier(int count) {
        this.count = count;
    }

    public String getType() {
        return "количество";
    }

    @Override
    public String getValue() {
        return "%d раз за день".formatted(count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountModifier that = (CountModifier) o;

        return count == that.count;
    }

    @Override
    public int hashCode() {
        return count;
    }

    @Override
    public String toString() {
        return "CountModifier{count=%d}".formatted(count);
    }
}
