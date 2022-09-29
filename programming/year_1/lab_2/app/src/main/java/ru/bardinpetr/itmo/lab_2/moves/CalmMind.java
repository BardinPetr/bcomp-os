package ru.bardinpetr.itmo.lab_2.moves;

import ru.ifmo.se.pokemon.*;

// #prevent_autogen

/**
 * Raises the user's Special Attack and Special Defense by one stage each.
 *
 * @author Bardin Petr
 */
public class CalmMind extends StatusMove {

    private static final double STATS_CHANCE = 1.0;

    public CalmMind() {
        super(Type.PSYCHIC, 0.0, 1.0, 0, 1);
    }

    @Override
    protected String describe() {
        return "Raises the user's Special Attack and Special Defense by one stage.";
    }

    @Override
    protected boolean checkAccuracy(Pokemon pokemon, Pokemon pokemon1) {
        return true;
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        super.applySelfEffects(pokemon);

        pokemon.addEffect(
                new Effect()
                        .chance(STATS_CHANCE)
                        .stat(Stat.SPECIAL_ATTACK, 1)
                        .stat(Stat.SPECIAL_DEFENSE, 1)
        );
    }
}
