package ru.bardinpetr.itmo.lab_2.moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

/**
 * Inflicts regular damage.
 *
 * @author Bardin Petr
 * @see <a href="https://pokemondb.net/move/tackle">PokemonDB/Tackle</a>
 */
public class Tackle extends PhysicalMove {

    public Tackle() {
        super(Type.NORMAL, 0.4, 1.0);
    }

    @Override
    protected String describe() {
        return "uses Tackle";
    }
}
