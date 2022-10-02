package ru.bardinpetr.itmo.lab_2;

import ru.bardinpetr.itmo.lab_2.pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class App {

    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Eevee("Eevee#1", 2));
        b.addAlly(new Leafeon("Leafeon#2", 3));
        b.addAlly(new Vikavolt("Vikavolt#3", 1));
        b.addFoe(new Celebi("Celebi#4", 1));
        b.addFoe(new Grubbin("Grubbin#5", 3));
        b.addFoe(new Charjabug("Charjabug#6", 2));
        b.go();
    }
}
