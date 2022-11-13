package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

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
}
