package ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces;

import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.Clothing;

public interface ICommonHumanAbilities {
    void live(Place place);

    void wear(Clothing thing);

    void have(PhysicalObject thing);
}
