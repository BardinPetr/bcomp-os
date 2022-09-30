package ru.bardinpetr.itmo.lab_2.pokemons;

import ru.bardinpetr.itmo.lab_2.moves.special.AncientPower;
import ru.bardinpetr.itmo.lab_2.moves.special.Psychic;
import ru.bardinpetr.itmo.lab_2.moves.status.CalmMind;
import ru.bardinpetr.itmo.lab_2.moves.status.DoubleTeam;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Celebi extends Pokemon {

    public Celebi(String name, int level) {
        super(name, level);
        setStats(100, 100, 100, 100, 100, 100);
        addType(Type.PSYCHIC);
        addType(Type.GRASS);
        addMove(new DoubleTeam());
        addMove(new AncientPower());
        addMove(new CalmMind());
        addMove(new Psychic());
    }
}
