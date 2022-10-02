package ru.bardinpetr.itmo.lab_2.moves.special;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

// #prevent_autogen

/**
 * Inflicts regular damage.  Has a 30% chance to paralyze the target.
 *
 * @author Bardin Petr
 * @see <a href="https://pokemondb.net/move/discharge">PokemonDB/Discharge</a>
 */
public class Discharge extends SpecialMove {

    private static final double AILMENT_CHANCE = 0.3;

    public Discharge() {
        super(Type.ELECTRIC, 0.8, 1.0);
    }

    @Override
    protected String describe() {
        return "uses Discharge";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);

        if (AILMENT_CHANCE > Math.random() && !pokemon.hasType(Type.ELECTRIC))
            Effect.paralyze(pokemon);
    }
}
