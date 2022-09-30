package ru.bardinpetr.itmo.lab_2.pokemons;

import ru.bardinpetr.itmo.lab_2.moves.physical.Tackle;
import ru.bardinpetr.itmo.lab_2.moves.status.DoubleTeam;
import ru.bardinpetr.itmo.lab_2.moves.status.Rest;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Eevee extends Pokemon {

    public Eevee(String name, int level) {
        super(name, level);
        setStats(55, 55, 50, 45, 65, 55);
        addType(Type.NORMAL);
        addMove(new DoubleTeam());
        addMove(new Rest());
        addMove(new Tackle());
    }
}
