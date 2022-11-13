package ru.bardinpetr.itmo.lab_3.creatures.interfaces;

import ru.bardinpetr.itmo.lab_3.properties.models.HeightSpecies;

public interface Heightable {
    double DISCRIMINATION_THRESHOLD = 150;

    double getHeight();

    void setHeight(double newHeight);

    default void setHeightSpecies() {
        System.out.println("In general case you should not set it");
    }

    default HeightSpecies getHeightSpecies() {
        return getHeight() > DISCRIMINATION_THRESHOLD ? HeightSpecies.NORMAL : HeightSpecies.SHORT;
    }
}
