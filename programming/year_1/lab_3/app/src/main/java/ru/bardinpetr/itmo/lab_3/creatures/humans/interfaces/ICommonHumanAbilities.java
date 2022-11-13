package ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces;

import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.place.Place;
import ru.bardinpetr.itmo.lab_3.things.wear.Clothing;

public interface ICommonHumanAbilities {
    void live(Place place);

    void wear(Clothing thing);

    void have(PhysicalObject thing);
}
