package ru.bardinpetr.itmo.lab_2.moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

/**
 * Inflicts regular damage.  Has a 30% chance to paralyze the target.
 * @author Bardin Petr
 */
public class Discharge extends SpecialMove {

    private static final double AILMENT_CHANCE = 0.3;

    public Discharge() {
        super(Type.ELECTRIC, 0.8, 1.0, 0, 1);
    }

    @Override
    protected String describe() {
        return "Has a 30% chance to paralyze the target.";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);

        if (AILMENT_CHANCE > Math.random()) Effect.paralyze(pokemon);
    }
}
