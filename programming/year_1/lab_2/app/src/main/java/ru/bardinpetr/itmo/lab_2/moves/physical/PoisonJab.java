package ru.bardinpetr.itmo.lab_2.moves.physical;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

/**
 * Inflicts regular damage.  Has a 30% chance to poison the target.
 *
 * @author Bardin Petr
 * @see <a href="https://pokemondb.net/move/poison-jab">PokemonDB/Poison Jab</a>
 */
public class PoisonJab extends PhysicalMove {

    private static final double AILMENT_CHANCE = 0.3;

    public PoisonJab() {
        super(Type.POISON, 0.8, 1.0);
    }

    @Override
    protected String describe() {
        return "uses Poison Jab";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);

        if (AILMENT_CHANCE > Math.random()) Effect.poison(pokemon);
    }
}
