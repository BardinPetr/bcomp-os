package ru.bardinpetr.itmo.lab_2.moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

/**
 * Inflicts regular damage.
 *
 * @author Bardin Petr
 * @see <a href="https://pokemondb.net/move/vice-grip">PokemonDB/Vice Grip</a>
 */
public class ViceGrip extends PhysicalMove {

    public ViceGrip() {
        super(Type.NORMAL, 0.55, 1.0);
    }

    @Override
    protected String describe() {
        return "uses Vice Grip";
    }
}
