package ru.bardinpetr.itmo.lab_2.pokemons;

import ru.bardinpetr.itmo.lab_2.moves.EnergyBall;
import ru.ifmo.se.pokemon.Type;

public class Leafeon extends Eevee {

    public Leafeon(String name, int level) {
        super(name, level);
        setStats(65, 110, 130, 60, 65, 95);
        addType(Type.GRASS);
        addMove(new EnergyBall());
    }
}
