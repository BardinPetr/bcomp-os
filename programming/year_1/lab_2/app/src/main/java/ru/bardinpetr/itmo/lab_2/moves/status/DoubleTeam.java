package ru.bardinpetr.itmo.lab_2.moves.status;

import ru.ifmo.se.pokemon.*;

/**
 * Raises the user's evasion by one stage.
 *
 * @author Bardin Petr
 * @see <a href="https://pokemondb.net/move/double-team">PokemonDB/Double Team</a>
 */
public class DoubleTeam extends StatusMove {

    private static final double STATS_CHANCE = 1.0;

    public DoubleTeam() {
        super(Type.NORMAL, 0.0, 1.0);
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
