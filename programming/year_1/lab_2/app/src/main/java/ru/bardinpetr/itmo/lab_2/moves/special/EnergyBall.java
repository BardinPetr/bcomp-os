package ru.bardinpetr.itmo.lab_2.moves.special;

import ru.ifmo.se.pokemon.*;

/**
 * Inflicts regular damage.  Has a 10% chance to lower the target's Special Defense by one stage.
 *
 * @author Bardin Petr
 * @see <a href="https://pokemondb.net/move/energy-ball">PokemonDB/Energy Ball</a>
 */
public class EnergyBall extends SpecialMove {

    private static final double STATS_CHANCE = 0.1;

    public EnergyBall() {
        super(Type.GRASS, 0.9, 1.0);
    }

    @Override
    protected String describe() {
        return "Has a 10% chance to lower the target's Special Defense by one stage.";
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        super.applyOppEffects(pokemon);

        pokemon.addEffect(
            new Effect().chance(STATS_CHANCE).stat(Stat.SPECIAL_DEFENSE, -1)
        );
    }
}
