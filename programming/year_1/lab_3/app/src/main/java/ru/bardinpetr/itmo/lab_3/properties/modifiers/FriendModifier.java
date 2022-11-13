package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

import java.util.Objects;

public class FriendModifier implements IModifier {
    private final Human other;

    public FriendModifier(Human other) {
        this.other = other;
    }

    public String getType() {
        return "дружит";
    }

    @Override
    public String getValue() {
        return "c %s".formatted(other.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendModifier that = (FriendModifier) o;

        return Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {
        return other != null ? other.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FriendModifier{other=%s}".formatted(other);
    }
}
