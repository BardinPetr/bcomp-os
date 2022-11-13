package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class NameModifier implements IModifier {

    public static final String TYPE = "имя";

    private final String name;

    public NameModifier(String name) {
        this.name = name;
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
        return name;
    }
}
