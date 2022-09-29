package ru.bardinpetr.itmo.lab_2.moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

/**
 * Raises the user's evasion by one stage.
 * @author Bardin Petr
 */
public class DoubleTeam extends StatusMove {

    private static final double STATS_CHANCE = 1.0;

    public DoubleTeam() {
        super(Type.NORMAL, 0.0, 1.0, 0, 1);
    }

    @Override
    protected String describe() {
        return "Raises the user's evasion by one stage.";
    }

    @Override
    protected boolean checkAccuracy(Pokemon pokemon, Pokemon pokemon1) {
        return true;
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        super.applySelfEffects(pokemon);

        pokemon.addEffect(
            new Effect().chance(STATS_CHANCE).stat(Stat.EVASION, 1)
        );
    }
}
