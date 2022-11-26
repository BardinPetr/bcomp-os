package ru.bardinpetr.itmo.lab_4.properties.models;

import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;

public enum Color implements IModifier {
    WHITE("белый"), CYAN("голубой"), YELLOW_CANARY("желто-канареечный"), ORANGE("оранжевый"), GREEN("зеленый");

    private final String text;

    Color(String text) {
        this.text = text;
    }

    public String getType() {
        return "цвет";
    }

    @Override
    public String getValue() {
        return text;
    }
}
