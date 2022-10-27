package ru.bardinpetr.itmo.lab_3.human;

import ru.bardinpetr.itmo.lab_3.properties.HeightSpecies;

public class ShortMan extends LivingHuman {

    public ShortMan(String name, String patronymic, String surname) {
        super(name, patronymic, surname);
    }

    public ShortMan(String name) {
        super(name);
    }

    @Override
    public HeightSpecies getHeightSpecies() {
        return HeightSpecies.SHORT;
    }
}
