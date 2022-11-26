package ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces;

import ru.bardinpetr.itmo.lab_4.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.things.place.Place;
import ru.bardinpetr.itmo.lab_4.things.wear.Clothing;

public interface ICommonHumanAbilities {
    void live(Place place);

    void wear(Clothing thing);

    void have(PhysicalObject thing);
}
