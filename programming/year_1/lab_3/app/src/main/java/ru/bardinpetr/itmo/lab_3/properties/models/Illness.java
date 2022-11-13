package ru.bardinpetr.itmo.lab_3.properties.models;

public enum Illness {
    SOME_ILLNESS, ANY("любые болезни");

    private final String commonName;

    Illness(String commonName) {
        this.commonName = commonName;
    }

    Illness() {
        this.commonName = name();
    }
}
