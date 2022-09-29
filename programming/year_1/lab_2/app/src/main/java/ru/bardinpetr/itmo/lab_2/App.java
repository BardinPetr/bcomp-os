package ru.bardinpetr.itmo.lab_2;

import ru.bardinpetr.itmo.lab_2.pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class App {

    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Celebi("a", 1));
        b.addAlly(new Leafeon("b", 1));
        b.addAlly(new Vikavolt("c", 1));
        b.addFoe(new Eevee("d", 1));
        b.addFoe(new Grubbin("e", 1));
        b.addFoe(new Charjabug("f", 1));
        b.go();
    }
}
