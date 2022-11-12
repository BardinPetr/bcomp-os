package ru.bardinpetr.itmo.lab_3.properties.interfaces;

public interface ModifierDescribable {
    default String getPreposition() {
        return "";
    }

    String getType();

    String convertValue(Object data);
}
