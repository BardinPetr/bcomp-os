package ru.bardinpetr.itmo.lab_2.pokemons;

import ru.bardinpetr.itmo.lab_2.moves.PoisonJab;

public class Vikavolt extends Charjabug {

    public Vikavolt(String name, int level) {
        super(name, level);
        setStats(77, 70, 90, 145, 75, 43);
        addMove(new PoisonJab());
    }
}
