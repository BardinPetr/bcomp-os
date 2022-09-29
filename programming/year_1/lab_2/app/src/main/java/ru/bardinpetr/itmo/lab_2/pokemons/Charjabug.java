package ru.bardinpetr.itmo.lab_2.pokemons;

import ru.bardinpetr.itmo.lab_2.moves.Discharge;
import ru.ifmo.se.pokemon.Type;

public class Charjabug extends Grubbin {

    public Charjabug(String name, int level) {
        super(name, level);
        setStats(57, 82, 95, 55, 75, 36);
        addType(Type.ELECTRIC);
        addMove(new Discharge());
    }
}
