package ru.bardinpetr.itmo.lab_2.moves.special;

import ru.ifmo.se.pokemon.*;

// #prevent_autogen

/**
 * Inflicts regular damage. Has a 10% chance to raise all of the user's stats one stage.
 *
 * @author Bardin Petr
 * @see <a href="https://pokemondb.net/move/ancient-power">PokemonDB/Ancient Power</a>
 */
public class AncientPower extends SpecialMove {

    private static final double STATS_CHANCE = 0.1;

    public AncientPower() {
        super(Type.ROCK, 0.6, 1.0);
    }

    @Override
    protected String describe() {
        return "Has a 10% chance to raise all of the user's stats by one stage.";
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        super.applySelfEffects(pokemon);

        pokemon.addEffect(
                new Effect()
                        .chance(STATS_CHANCE)
                        .stat(Stat.ATTACK, 1)
                        .stat(Stat.DEFENSE, 1)
                        .stat(Stat.SPECIAL_ATTACK, 1)
                        .stat(Stat.SPECIAL_DEFENSE, 1)
                        .stat(Stat.SPEED, 1)
        );
    }
}
