package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

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
}
