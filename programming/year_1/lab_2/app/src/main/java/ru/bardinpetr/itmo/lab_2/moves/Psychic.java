package ru.bardinpetr.itmo.lab_2.moves;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

/**
 * Inflicts regular damage.  Has a 10% chance to lower the target's Special Defense by one stage.
 * @author Bardin Petr
 */
public class Psychic extends SpecialMove {

    private static final double STATS_CHANCE = 0.1;

    public Psychic() {
        super(Type.PSYCHIC, 0.9, 1.0, 0, 1);
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
