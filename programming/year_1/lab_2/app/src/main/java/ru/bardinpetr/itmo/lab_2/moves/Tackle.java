package ru.bardinpetr.itmo.lab_2.moves;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Type;

// #prevent_autogen

/**
 * Inflicts regular damage.
 *
 * @author Bardin Petr
 */
public class Tackle extends PhysicalMove {

    public Tackle() {
        super(Type.NORMAL, 0.4, 1.0, 0, 1);
    }

    @Override
    protected String describe() {
        return "Inflicts regular damage with no additional effect.";
    }
}
