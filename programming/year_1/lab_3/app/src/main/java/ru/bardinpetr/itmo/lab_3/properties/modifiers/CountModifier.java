package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class CountModifier implements IModifier {
    private final int count;

    public CountModifier(int count) {
        this.count = count;
    }

    @Override
    public String getType() {
        return "количество раз";
    }

    @Override
    public String getValue() {
        return String.valueOf(count);
    }
}
