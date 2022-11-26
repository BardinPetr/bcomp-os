package ru.bardinpetr.itmo.lab_4.properties.models;

public enum Illness {
    SOME_ILLNESS, ANY("любые болезни");

    private final String commonName;

    Illness(String commonName) {
        this.commonName = commonName;
    }

    Illness() {
        this.commonName = name();
    }

    @Override
    public String toString() {
        return commonName;
    }
}
