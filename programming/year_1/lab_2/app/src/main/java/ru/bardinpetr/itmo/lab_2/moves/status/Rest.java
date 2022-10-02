package ru.bardinpetr.itmo.lab_2.moves.status;

import ru.ifmo.se.pokemon.*;

// #prevent_autogen

/**
 * User falls to sleep and immediately regains all its HP.  If the user has another major status effect, sleep will replace it.  The user will always wake up after two turns, or one turn with early bird.  This move fails if the Pokémon cannot fall asleep due to uproar, insomnia, or vital spirit.  It also fails if the Pokémon is at full health or is already asleep.
 *
 * @author Bardin Petr
 * @see <a href="https://pokemondb.net/move/rest">PokemonDB/Rest</a>
 */
public class Rest extends StatusMove {

    public Rest() {
        super(Type.PSYCHIC, 0.0, 1.0);
    }

    @Override
    protected String describe() {
        return "uses Rest";
    }

    @Override
    protected boolean checkAccuracy(Pokemon pokemon, Pokemon pokemon1) {
        return true;
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        super.applySelfEffects(pokemon);

        pokemon.restore();
        pokemon.addEffect(
                new Effect().condition(Status.SLEEP).attack(0.0).turns(2)
        );
    }
}
