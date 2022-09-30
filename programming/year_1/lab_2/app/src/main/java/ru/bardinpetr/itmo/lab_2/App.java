package ru.bardinpetr.itmo.lab_2;

import ru.bardinpetr.itmo.lab_2.pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class App {

    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Celebi("Celebi", 10));
        b.addAlly(new Leafeon("Leafeon", 53));
        b.addAlly(new Vikavolt("Vikavolt", 43));
        b.addFoe(new Eevee("Eevee", 42));
        b.addFoe(new Grubbin("Grubbin", 60));
        b.addFoe(new Charjabug("Charjabug", 34));
        b.go();
    }
}
