package ru.bardinpetr.itmo.lab_4.properties.models;

public enum WearType {
    MEDICAL_GOWN("халат"), CAP("колпак"), HAT("шляпа"), TROUSERS("штаны"), SHIRT("рубашка"), TIE("галстук");

    private final String text;

    WearType(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
