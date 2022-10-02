package ru.bardinpetr.itmo.lab_2.pokemons;

import ru.bardinpetr.itmo.lab_2.moves.physical.ViceGrip;
import ru.bardinpetr.itmo.lab_2.moves.status.Rest;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Grubbin extends Pokemon {

    public Grubbin(String name, int level) {
        super(name, level);
        setStats(47, 62, 45, 55, 45, 46);
        addType(Type.BUG);
        addMove(new Rest());
        addMove(new ViceGrip());
    }
}
